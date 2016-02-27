package com.kedi.routes

import com.kedi.main.Boot
import com.kedi.common.utils.StringUtils._
import spray.http.MediaTypes._
import com.kedi.process.processors.SupplierRequest

trait KundaRoute extends BaseRoute {
  // val rp = Boot.actor("  val rp = Boot.actor("requestprocessor").get").get
  val rp3 = Boot.actor("requestprocessor").get

  val supplierRoute = basepath {
    path("suppliers") {
      get {
        respondWithMediaType(`application/json`) {
          ctx => rp3 ! (ctx, SupplierRequest())
        }
      }
    } ~
    path("ping1") {
      get {
        complete("pong1")
      }
    }
  }
  
  routes += supplierRoute
}
