package org.tashaxing.SpringbootScalaDemo.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation._
import org.tashaxing.SpringbootScalaDemo.model.ScalaModel
import org.tashaxing.SpringbootScalaDemo.repository.ScalaModelQuery

import slick.jdbc.MySQLProfile.api._
import org.tashaxing.SpringbootScalaDemo.model.SlickDB._
import scala.concurrent.Await
import scala.concurrent.duration.Duration

import scala.collection.mutable
import scala.util.Random
import java.util.List
import javax.validation.Valid

import org.springframework.validation.BindingResult

@RestController // here must be restcontroller
@RequestMapping(Array("/scalatest"))
class ScalaTestController @Autowired()(private val scalaModelQuery: ScalaModelQuery)
{
    // slick db config, usually it is in a config file
    val db = Database.forURL(
        url = "jdbc:mysql://localhost:3306/db_example?useUnicode=true&characterEncoding=UTF-8&useSSL=false",
        driver = "com.mysql.jdbc.Driver",
        user = "springuser",
        password = "ThePassword")

    // ---- normal operation
    // root test
    @GetMapping
    def root = "hello scala springboot"

    // get test with short mapping
    @GetMapping(Array("/string1"))
    def getTest1(): String = {
        return "scala test1 results"
    }

    // get test with whole mapping
    @RequestMapping(value = Array("/string2"), method = Array(RequestMethod.GET))
    def getTest2(): String = {
        return "scala test2 results"
    }

    // get int list
    @GetMapping(Array("/list"))
    def getIntList(): Array[Int] = {
        return Array(1, 2, 3, 4)
    }

    // get object(frontend will get json structure)
    @GetMapping(Array("/object"))
    def getObject(): ScalaModel = {
        val model = new ScalaModel // if it is defined with class
        model.id = 3L
        model.name = "lucy"
        model.age = 21

//        val model = ScalaModel(5L, "lily", 23) // if it is defined with abstract class
        return model
    }

//    // get object
//    @GetMapping(Array("/slickobject"))
//    def getSlickObject(): UserInfo = {
//        val userInfo = UserInfo(5L, "alice", 17)
//        return userInfo
//    }

    // get object with param
    @GetMapping(Array("/object/{id}"))
    def getObject(@PathVariable("id") id: Long): ScalaModel = {
        val random_age = Random.nextInt(10)
        val model = new ScalaModel
        model.id = id
        model.name = "tom"
        model.age = 20 + random_age
        return model
    }

    // get list of object
    @GetMapping(Array("/objectlist"))
    def getObjectList(): Array[ScalaModel] =
    {
        val model_list = mutable.ArrayBuffer[ScalaModel]()
        for (i <- 0 to 9)
        {
            val model = new ScalaModel
            model.id = i.toLong
            model.name = "lily"
            model.age = 20 + i

            model_list += model
        }
        return model_list.toArray
    }

    // post single param
    @PostMapping(Array("/upload"))
    def postInfo(@PathVariable("name") name: String): String =
    {
        return "post param $name got"
    }

    // ---- database operation

    /*jpa*/
    // get all recorde(remember to use java List not scala List)
    @GetMapping(Array("/listmodel"))
    def listmodel(): List[ScalaModel] =
    {
        val res = scalaModelQuery.findAll

        // do sth here

        return res
    }

    // get
    @GetMapping(value = Array("/findid/{id}"))
    def findById(@PathVariable(value = "id") id: Long): ScalaModel =
    {
        return scalaModelQuery.find(id)
    }

    @GetMapping(Array("/findname/{name}"))
    def findByName(@PathVariable("name") name: String): List[ScalaModel] =
    {
        return scalaModelQuery.findByName(name)
    }

    // post
    @PostMapping(Array("/delete/{id}"))
    def deleteById(@PathVariable("id") id: Long): String =
    {
        val res = scalaModelQuery.delete(id)
        return "delete success"
    }

    @RequestMapping(value = Array("/add"), method = Array(RequestMethod.POST))
    def save(@RequestBody scalaModel: ScalaModel): ScalaModel =
    {
        // notice here use RequestBody
        val res = scalaModelQuery.save(scalaModel)
        return res
    }

    @PostMapping(Array("/update"))
    def update(@RequestBody scalaModel: ScalaModel, bindingResult: BindingResult): ScalaModel =
    {
        val res = scalaModelQuery.update(scalaModel)
        return res
    }

    /*slick*/
    @GetMapping(Array("/getslick/{name}"))
    def getSlickModel(@PathVariable("name") name: String): UserInfoObject =
    {
        val slickres = Await.result(db.run(slick_table.filter(_.name === name).result), Duration.Inf)

        val userInfoObject = new UserInfoObject
        userInfoObject.id = slickres(0).id
        userInfoObject.name = slickres(0).name
        userInfoObject.age = slickres(0).age

        return userInfoObject
    }

    @GetMapping(Array("/listslick"))
    def listSlickModel(): Array[UserInfoObject] =
    {
        val slickres = Await.result(db.run(slick_table.result), Duration.Inf)
//        val slickres = Await.result(db.run(slick_table.filter(_.age < 22).result), Duration.Inf)
        val res = mutable.ArrayBuffer[UserInfoObject]()
        slickres.map(
            record => {
                val userInfoObject = new UserInfoObject
                userInfoObject.id = record.id
                userInfoObject.name = record.name
                userInfoObject.age = record.age
                res += userInfoObject
        })
        return res.toArray
    }

    @PostMapping(Array("/slickadd"))
    def slicksave(@RequestBody userInfoObject: UserInfoObject): String =
    {
        val userInfo = UserInfo(userInfoObject.id, userInfoObject.name, userInfoObject.age)
        val userArray = Array[UserInfo](userInfo)
        Await.result(db.run(slick_table ++= userArray), Duration.Inf)

        return "save success"
    }

    @PostMapping(Array("/slickupdate"))
    def slickupdate(@RequestBody userInfoObject: UserInfoObject): String =
    {
        val userInfo = UserInfo(userInfoObject.id, userInfoObject.name, userInfoObject.age)
        val userArray = Array[UserInfo](userInfo)
        Await.result(db.run(slick_table.filter(_.name === userInfo.name).delete), Duration.Inf)
        Await.result(db.run(slick_table ++= userArray), Duration.Inf)

        return "update success"
    }

    @PostMapping(Array("/slickdelete/{id}"))
    def slickupdate(@PathVariable("id") id: Long): String =
    {
        Await.result(db.run(slick_table.filter(_.id === id).delete), Duration.Inf)

        return "delete success"
    }

}
