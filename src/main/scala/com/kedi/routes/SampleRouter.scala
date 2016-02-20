package com.kedi.routes

import spray.http._
import MediaTypes._
import com.kedi.main.Boot
import com.kedi.process.processors.SampleRequest
import com.kedi.process.processors.OneParameterRequest
import com.kedi.process.processors.TwoParameterRequest
import com.kedi.process.processors.SampleRequest
import com.kedi.process.processors.ColorRequest


trait SampleRouter extends BaseRoute {

  val sampleRoute =
    path("test") {
      get {
        respondWithMediaType(`text/html`) {
          // XML is marshalled to `text/xml` by default, so we simply override here
          complete {
            <html>
              <body>
                <h1>Say hello to <i>spray-routing</i> on <i>spray-can</i>!</h1>
              </body>
            </html>
          }
        }
      }
    }

  val sampleRoute2 = basepath {
    path("test") {
      get {
        respondWithMediaType(`text/html`) {
          // XML is marshalled to `text/xml` by default, so we simply override here
          complete {
            <html>
              <body>
                <h1>Say hello to <i>spray-routing</i> on <i>spray-can</i>!</h1>
              </body>
            </html>
          }
        }
      }
    }
  }

  val rp = Boot.actor("requestprocessor").get

  val sampleRoute3 = basepath {
    path("sample") {
      get {
        respondWithMediaType(`text/html`) {
          ctx => rp ! (ctx, SampleRequest())
        }
      }
    } ~
    path("ping") {
      get {
        complete("pong")
      }
    } ~ 
    path("oneparam") {
      get {           
        respondWithMediaType(`text/html`) {
        parameter('phoneNo) {
          (phoneNo) => ctx => rp ! (ctx, OneParameterRequest(phoneNo))
           }
        }
      }
    } ~ 
    path("twoparams") {
      get {           
        respondWithMediaType(`text/html`) {
           ctx => rp ! (ctx, OneParameterRequest("hello"))
        }
      }
    } ~ 
    path("twoparams2") {
      get {           
        respondWithMediaType(`text/html`) {
            parameter('name, 'phoneNo?) {
           (name, phoneNo) => ctx => rp ! (ctx, TwoParameterRequest(name, phoneNo.getOrElse("someno"))) 
           }
        }
      }
    } ~
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
  routes += sampleRoute2
  routes += sampleRoute3
}
