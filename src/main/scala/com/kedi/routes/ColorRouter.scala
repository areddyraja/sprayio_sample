package com.kedi.routes

import com.kedi.main.Boot
import com.kedi.process.processors.ColorRequest
import com.kedi.process.processors.TwoParameterRequest
import com.kedi.common.utils.StringUtils._

import spray.http.MediaTypes._
trait ColorRouter extends BaseRoute {
  val rp = Boot.actor("colorProcessor").get

  val sampleRoute = basepath {

    path("colors") {
      get {
        respondWithMediaType(`application/json`) {
          parameter('name, 'red?, 'green?, 'blue?) {
            (name, red, green, blue) =>
              ctx => {
                rp ! (ctx, ColorRequest(name, 0, 0, 0))
              }
          }
        }
      }
    }

  }

  routes += sampleRoute
}
