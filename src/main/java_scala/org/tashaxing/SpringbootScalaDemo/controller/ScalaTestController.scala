package org.tashaxing.SpringbootScalaDemo.controller

import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation._
import org.tashaxing.SpringbootScalaDemo.model.ScalaModel

import scala.beans.BeanProperty
import scala.collection.mutable
import scala.util.Random

case class MyMessage(@BeanProperty var id: Int, var value: String)

@RestController // here must be restcontroller
@RequestMapping(Array("/scalatest"))
class ScalaTestController {
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
        //        val model = new ScalaModel // if it is defined with class
        //        model.id = 3L
        //        model.name = "lucy"
        //        model.age = 21

        val model = ScalaModel(5L, "lily", 23) // if it is defined with abstract class
        return model
    }

    // get object with param
    @GetMapping(Array("/object/{id}"))
    def getObject(@PathVariable("id") id: Long): ScalaModel = {
        val random_age = Random.nextInt(10)
        val model = ScalaModel(id, "tom", 20 + random_age)
        return model
    }

    // get list of object
    @GetMapping(Array("/objectlist"))
    def getObjectList(): Array[ScalaModel] =
    {
        val model_list = mutable.ArrayBuffer[ScalaModel]()
        for (i <- 0 to 9)
        {
            val model = ScalaModel(i.toLong, "mary", 30)
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


}
