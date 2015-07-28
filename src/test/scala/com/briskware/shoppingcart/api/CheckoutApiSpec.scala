package com.briskware.shoppingcart.api

import spray.testkit.Specs2RouteTest

import spray.http._
import MediaTypes._

import org.specs2.mutable.Specification
import spray.routing.{MalformedRequestContentRejection, RequestEntityExpectedRejection, RejectionError, HttpService}
import scala.concurrent.duration._


import org.specs2.time.NoTimeConversions


/**
 * Created by stefan on 28/07/2015.
 */
class CheckoutApiSpec extends Specification
with Specs2RouteTest with HttpService with NoTimeConversions {

  val actorRefFactory = system

  class ApiServiceTester extends ApiService {
    def actorRefFactory = system
  }

  val service = new ApiServiceTester

  val route = service.routes

  implicit val routeTestTimeout = RouteTestTimeout(5 seconds)

  "The ApiService" should {
    "return a result for a valid cart" in {
      Post(s"/api/checkout", HttpEntity(`application/json`, """{"products":[{"kind":"Apple"},{"kind":"Apple"},{"kind":"Orange"},{"kind":"Apple"}]}""")) ~> route ~> check {
        body.asString.toDouble must be equalTo (1.45D)
      }
    }
  }

  "The ApiService" should {
    "return zero for an empty cart" in {
      Post(s"/api/checkout", HttpEntity(`application/json`, """{"products":[]}""")) ~> route ~> check {
        body.asString.toInt must be equalTo (0)
      }
    }
  }

  "The ApiService" should {
    "fail with an empty request" in {
      Post(s"/api/checkout", HttpEntity(`application/json`, """""")) ~> route ~> check {
        rejection must beLike {
          case RequestEntityExpectedRejection => ok
        }
      }
    }
  }

  "The ApiService" should {
    "fail with an invalid product == Pear" in {
      Post(s"/api/checkout", HttpEntity(`application/json`, """{"products":[{"kind":"Apple"},{"kind":"Pear"},{"kind":"Orange"},{"kind":"Apple"}]}""")) ~> route ~> check {
        rejection must beLike {
          case MalformedRequestContentRejection(x, _) if x.contains("Pear") => ok
        }
      }
    }
  }

}
