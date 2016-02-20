package com.kedi.delegate.actors
import spray.routing.RequestContext

trait RequestHandler {
  def process(rq: Request): Unit
}

trait Request {
}