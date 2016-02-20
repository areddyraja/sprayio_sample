package com.kedi.routes

import scala.collection.mutable.MutableList

import org.slf4j.LoggerFactory

import spray.http.AllOrigins
import spray.http.HttpHeaders
import spray.http.HttpMethods
import spray.routing.HttpService

import spray.routing.Route

trait BaseRoute extends HttpService {
  val Log = LoggerFactory getLogger getClass
  val routes = new MutableList[Route]

  def addCorsHeaders =
    respondWithHeaders(
      HttpHeaders.`Access-Control-Allow-Origin`(AllOrigins),
      HttpHeaders.`Access-Control-Allow-Methods`(HttpMethods.GET, HttpMethods.POST),
      HttpHeaders.`Access-Control-Allow-Headers`("Content-Type, Accept"),
      HttpHeaders.Connection("Keep-Alive"))

  def basepath(route: Route) = compressResponseIfRequested() {
    addCorsHeaders(
      optionalCookie("KEDISESSIONID") { cookie ⇒
        pathPrefix("kedi" / "api" / "v1") {
          route
        }
      })
  }

    
}