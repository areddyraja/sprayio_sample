package com.kedi.process.processors

import com.kedi.delegate.actors.Request
import com.kedi.data.dao.KundaDBSchema.Supplier
import com.kedi.services.SupplierService
import spray.json._
import spray.json.DefaultJsonProtocol._
import spray.routing.RequestContext

case class SupplierRequest() extends Request

object supplierJsonProtocol extends DefaultJsonProtocol {
  //incase implicit color object is defined
  //implicit val colorFormat = jsonFormat4(Color.apply)
  implicit val supplierFormat = jsonFormat4(Supplier)
}

object KundaRequestProcessor {
  def process(rc: RequestContext, request: SupplierRequest) = {
    request match {
      case rq: SupplierRequest => {
        import supplierJsonProtocol._
        //supplier collection to be given
        SupplierService.getSuppliers()
        rc.complete(SupplierService.getSuppliers().toString())
      }
    }
  }
}