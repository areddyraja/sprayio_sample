package com.kedi.main

import akka.actor.ActorSystem
import akka.actor.Props
import akka.actor.ActorRef
import akka.actor.actorRef2Scala
import akka.routing.RoundRobinPool
import akka.io.IO
import spray.can.Http
import akka.pattern.ask
import akka.util.Timeout
import scala.concurrent.duration._
import com.kedi.delegate.actors.RequestProcessingActor

object Boot extends App {

  private var actorMap = Map[String, ActorRef]()
  // we need an ActorSystem to host our application in
  implicit val system = ActorSystem("on-spray-can")

  //initialize all the services
  initializeActors
  initServices

  // create and start our service actor
  val service = system.actorOf(Props[HttpServiceActor], "demo-service")

  implicit val timeout = Timeout(5.seconds)
  // start a new HTTP server on port 8080 with our service actor as the handler
  IO(Http) ? Http.Bind(service, interface = "localhost", port = 8080)

  //create all the actors before hand
  private def initializeActors(implicit system: ActorSystem) = {
    //val requestProps = Props(new RequestProcessingActor).withRouter(RoundRobinPool(2)).withDispatcher("akka.actor.service-dispatcher")
    val requestProps = Props(new RequestProcessingActor) withRouter (new RoundRobinPool(2))

    actorMap += (("requestprocessor", system.actorOf(requestProps)))
  }

  def actor(name: String) = actorMap.get(name)

  private def initServices = {

  }
}
