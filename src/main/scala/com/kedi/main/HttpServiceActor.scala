package com.kedi.main

import akka.actor.Actor
import spray.routing._
import spray.http._
import MediaTypes._
import com.kedi.routes.SampleRouter
import com.kedi.routes.ColorRouter
import com.kedi.routes.KundaRoute

// we don't implement our route structure directly in the service actor because
// we want to be able to test it independently, without having to spin up an actor
class HttpServiceActor extends Actor with SampleRouter with ColorRouter with KundaRoute{

  // the HttpService trait defines only one abstract member, which
  // connects the services environment to the enclosing actor or test
  def actorRefFactory = context

  // this actor only runs our route, but you could add
  // other things here, like request stream processing
  // or timeout handling
  def receive = runRoute(routes reduceLeft (_ ~ _))
}

// this trait defines our service behavior independently from the service actor
trait MyService extends HttpService {

  val myRoute =
    path("") {
      get {
        respondWithMediaType(`text/html`) { // XML is marshalled to `text/xml` by default, so we simply override here
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


