package org.tashaxing.SpringbootScalaDemo.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.beans.factory.annotation.Autowired
import scala.reflect.ClassTag
import java.lang.Long
import org.springframework.data.domain.Page
import java.util.List
import org.springframework.stereotype.Service
import javax.transaction.Transactional
import java.lang.Boolean
import org.springframework.data.domain.PageRequest

// base query helper for all datatype and table
@Service
abstract class BaseQuery[T: ClassTag] {

  /** spring data jpa dao */
  @Autowired val jpaRepository: JpaRepository[T, Long] = null

  /**
    * add record
    *
    * @param s
    * @return T
    */
  def save[S <: T](s: S): T = jpaRepository.save(s)

  /**
    * delete record by id
    *
    * @param id 数据Id
    * @return Unit
    */
  @Transactional
  def delete(id: Long): Unit = jpaRepository.delete(id)

  /**
    * delete by batch
    *
    * @param lists
    * @return Unit
    */
  @Transactional
  def delete(lists: List[T]): Unit = jpaRepository.delete(lists);

  /**
    * update recorde by id
    *
    * @param s
    * @return T
    */
  @Transactional
  def update[S <: T](s: S) = jpaRepository.save(s)

  /**
    * find recorde by id
    *
    * @param id 数据Id
    * @return T
    */
  def find[S <: T](id: Long): T = jpaRepository.findOne(id)

  /**
    * query all
    *
    * @return List[T]
    */
  def findAll[S <: T]: List[T] = jpaRepository.findAll

  /**
    * query by id batch
    *
    * @return List[T]
    */
  def findAll[S <: T](ids: List[Long]): List[T] = jpaRepository.findAll(ids)

  /**
    * count
    *
    * @return Long
    */
  def count: Long = jpaRepository.count

  /**
    * check recorde exits
    *
    * @param id
    * @return Boolean
    */
  def exist(id: Long): Boolean = jpaRepository.exists(id)

  /**
    * query page
    *
    * @param page     start page
    * @param pageSize page number
    * @return Page[T]
    */
  def page[S <: T](page: Int, pageSize: Int): Page[T] = {
    var rpage = if (page < 1) 1 else page;
    var rpageSize = if (pageSize < 1) 5 else pageSize;
    jpaRepository.findAll(new PageRequest(rpage - 1, pageSize))
  }

}
