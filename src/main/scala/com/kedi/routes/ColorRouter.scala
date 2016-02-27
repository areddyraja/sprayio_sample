package com.kedi.routes

import com.kedi.main.Boot
import com.kedi.process.processors.ColorRequest
import com.kedi.process.processors.TwoParameterRequest
import com.kedi.common.utils.StringUtils._

import spray.http.MediaTypes._
trait ColorRouter extends BaseRoute {
  // val rp = Boot.actor("  val rp = Boot.actor("requestprocessor").get").get
  val rp2 = Boot.actor("requestprocessor").get

  val colorRoute = basepath {

    path("colors") {
      get {
        respondWithMediaType(`application/json`) {
          parameter('name, 'red.?, 'green.?, 'blue.?) {
            (name, red, green, blue) =>
              ctx => {
                import com.kedi.common.utils.StringUtils.StringExtensions
                val redone = 10; //red.getOrElse("10").toString.toIntOption.get
                val greenone = green.getOrElse("10").toString.toIntOption.get
                val blueone = blue.getOrElse("10").toString.toIntOption.get
                rp2 ! (ctx, ColorRequest(name, 10, greenone, 100))
              }
          }
        }
      }
    }

  }

  routes += colorRoute
}
