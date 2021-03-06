package com.kedi.delegate.actors

import org.slf4j.LoggerFactory
import com.kedi.process.processors.ColorRequest
import com.kedi.process.processors.ColorRequestProcessor
import com.kedi.process.processors.SampleRequestProcessor
import akka.actor.Actor
import spray.routing.RequestContext
import com.kedi.process.processors.SampleRequest
import com.kedi.process.processors.KundaRequestProcessor
import com.kedi.process.processors.SupplierRequest

class RequestProcessingActor extends Actor {
  val Log = LoggerFactory getLogger getClass

  def receive = {
    case (rc: RequestContext, rq: ColorRequest)  => ColorRequestProcessor.process(rc, rq)
    case (rc: RequestContext, rq: SampleRequest) => SampleRequestProcessor.process(rc, rq)
    case (rc: RequestContext, rq: SupplierRequest) => {
      println("Supplier Message ")
      KundaRequestProcessor.process(rc, rq)
    }
    case (rc: RequestContext, rq: Any) => println("Unknown Request" + rq)
    case _                             => println("Unknown Message: ")
  }
}