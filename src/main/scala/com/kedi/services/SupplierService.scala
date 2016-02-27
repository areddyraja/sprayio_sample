package com.kedi.services

import com.kedi.data.dao.KundaDBSchema
import com.kedi.data.dao.KundaDBSchema.Supplier

object SupplierService {
  def getSuppliers(){
   val suppliers: List[Supplier]  =  KundaDBSchema.allSuppliers
   suppliers.foreach(x=>println(x.toString()))
   suppliers
  }
}