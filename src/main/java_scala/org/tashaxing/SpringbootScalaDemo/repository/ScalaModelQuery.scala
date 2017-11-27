package org.tashaxing.SpringbootScalaDemo.repository

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.tashaxing.SpringbootScalaDemo.model.ScalaModel
import java.util.List

@Service
class ScalaModelQuery extends BaseQuery[ScalaModel]
{
    @Autowired val scalaModelRepository: ScalaModelRepository = null

    def findByName(name: String): List[ScalaModel] = scalaModelRepository.findByName(name)

//    def findNameOfAge(): List[Object] = scalaModelRepository.findNameOfAge()
}
