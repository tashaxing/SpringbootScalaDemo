package org.tashaxing.SpringbootScalaDemo.model

import javax.persistence.{Entity, GeneratedValue, Id}
import javax.validation.constraints.NotNull

import org.hibernate.validator.constraints.{NotBlank, NotEmpty}

import scala.annotation.meta.field
import scala.beans.BeanProperty
import slick.jdbc.MySQLProfile.api._
import slick.lifted.TableQuery

object SlickDB
{
//    @Entity
    // in springboot, we must add some annotation for case class for slick
//    @Entity
//    case class UserInfo(
//        @(Id @field) @(GeneratedValue @field) @BeanProperty var id: Long,
//        @BeanProperty @(NotBlank @field) var name: String, // @field is a must
//        @BeanProperty @(NotNull @field) var age: Int
//    )
    case class UserInfo(id: Long, name: String, age: Int)

    // define an entity class with contructor used for spring serialize
    @Entity
    class UserInfoObject
    {
        @Id
        @GeneratedValue
        @BeanProperty
        var id: Long = _

        @BeanProperty
        @NotBlank            // we can make sure it not empty
        var name: String = _

        @BeanProperty
        @NotNull
        var age: Int = 18 // we can define default value here
    }

    class SlickModelTable(tag: Tag) extends Table[UserInfo](tag, "scala_model")
    {
        def id = column[Long]("id", O.PrimaryKey)
        def name = column[String]("name")
        def age = column[Int]("age")
        def * = (id, name, age) <> (UserInfo.tupled, UserInfo.unapply)
    }
    def slick_table = TableQuery[SlickModelTable]
}


