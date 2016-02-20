package com.kedi.process.processors

import com.kedi.delegate.actors.Request

import spray.routing.RequestContext

case class SampleRequest() extends Request
case class OneParameterRequest(phoneNo: String) extends Request
case class TwoParameterRequest(name: String, phoneNo: String) extends Request


object SampleRequestProcessor {
  def process(rc: RequestContext, request: Request) = {
    request match {
      case rq: SampleRequest       => rc.complete("OK")
      case rq: OneParameterRequest => rc.complete(rq.phoneNo)
       case rq: TwoParameterRequest => rc.complete(rq.phoneNo + "," + rq.phoneNo)
    }
  }
  
  def someThingNew(r: OneParameterRequest) =  {
    
  }
}