package org.tashaxing.SpringbootScalaDemo.repository

import org.springframework.data.jpa.repository.{JpaRepository, Query}
import org.tashaxing.SpringbootScalaDemo.model.ScalaModel

// remember to use java List and Long here
import java.util.List
import java.lang.Long

trait ScalaModelRepository extends JpaRepository[ScalaModel, Long]
{
    // normal find function from db
    def findByName(name: String): List[ScalaModel]

    // self defined function from db by sql query
//    @Query(value = "SELECT name FROM scala_model")
//    def findNameOfAge(): List[Object]
}