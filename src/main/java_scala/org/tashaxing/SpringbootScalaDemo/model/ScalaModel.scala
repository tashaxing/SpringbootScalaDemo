package org.tashaxing.SpringbootScalaDemo.model

import java.lang.Long
import javax.persistence.{Entity, GeneratedValue, Id}

import scala.beans.BeanProperty
import org.hibernate.validator.constraints.NotEmpty

import scala.annotation.meta.field

@Entity
case class ScalaModel(
    @(Id @field) @(GeneratedValue @field) @BeanProperty var id: Long,
    @BeanProperty @(NotEmpty @field) var name: String, // @field is a must
    @BeanProperty @(NotEmpty @field) var age: Int
)

//@Entity
//class ScalaModel
//{
//    @Id
//    @GeneratedValue
//    @BeanProperty
//    var id: Long = _
//
//    @BeanProperty
//    @NotEmpty            // we can make sure it not empty
//    var name: String = _
//
//    @BeanProperty
//    @NotEmpty
//    var age: Int = 18 // we can define default value here
//}


