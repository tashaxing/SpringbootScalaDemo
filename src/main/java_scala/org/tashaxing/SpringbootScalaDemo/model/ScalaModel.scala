package org.tashaxing.SpringbootScalaDemo.model

import java.lang.Long
import javax.persistence.{Entity, GeneratedValue, Id, Table}
import javax.validation.constraints.NotNull

import scala.beans.BeanProperty
import org.hibernate.validator.constraints.{NotBlank, NotEmpty}

import scala.annotation.meta.field

//@Table(name = "scala_model") // define mysql table name
//@Entity
//case class ScalaModel(
//    @(Id @field) @(GeneratedValue @field) @BeanProperty var id: Long,
//    @BeanProperty @(NotEmpty @field) var name: String, // @field is a must
//    @BeanProperty @(NotEmpty @field) var age: Int
//)

@Table(name = "scala_model") // define mysql table name
@Entity
class ScalaModel
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


