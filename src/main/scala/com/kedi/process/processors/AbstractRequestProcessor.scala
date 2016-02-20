package com.kedi.process.processors

import com.kedi.delegate.actors.Request
import com.kedi.delegate.actors.RequestHandler
import spray.routing.RequestContext

/**
 * Not used in this class for now.
 * Should be implemented later.
 */
class AbstractRequestProcessor(rq: RequestContext) extends RequestHandler {
  override def process(request: Request) = {}
}