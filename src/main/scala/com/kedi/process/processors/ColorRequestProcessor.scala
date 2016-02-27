package com.kedi.process.processors

import com.kedi.delegate.actors.Request

import spray.json._
import spray.json.DefaultJsonProtocol._

import spray.routing.RequestContext

case class ColorRequest(name: String, red: Int, green: Int, blue: Int) extends Request

case class Color(name: String, red: Int, green: Int, blue: Int)
//in case of implicit color object
//object Color

object MyJsonProtocol extends DefaultJsonProtocol {
  //incase implicit color object is defined
  //implicit val colorFormat = jsonFormat4(Color.apply)
  implicit val colorFormat = jsonFormat4(Color)
}

object ColorRequestProcessor {
  def process(rc: RequestContext, request: ColorRequest) = {
    request match {
      case rq: ColorRequest => {
        import MyJsonProtocol._
        println(rq.red + " " + rq.green + " " + rq.blue)
        rc.complete(Color(rq.name, 10, 10, rq.blue).toJson.toString())
      }
    }
  }
}