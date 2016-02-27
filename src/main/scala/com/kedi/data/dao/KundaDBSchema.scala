package com.kedi.data.dao

import slick.driver.PostgresDriver.simple._
import java.sql.Timestamp
import scala.slick.jdbc.{StaticQuery ⇒ Q }
import Q.interpolation

/**
 * @author reddyraja
 */
object KundaDBSchema {
  def db = KundaDataSource.db

  case class Supplier(snum: String, sname: String, status: Int, city: String)
  class SupplierTable(tag: Tag) extends Table[Supplier](tag, "suppliers") {
    def snum = column[String]("snum")
    def sname = column[String]("sname")
    def status = column[Int]("status")
    def city = column[String]("city")
    def * = (snum, sname, status, city) <> (Supplier.tupled, Supplier.unapply _)
  }

  val Suppliers = TableQuery[SupplierTable]

  def allSuppliers = db withSession (implicit session ⇒ Suppliers list)
  
}