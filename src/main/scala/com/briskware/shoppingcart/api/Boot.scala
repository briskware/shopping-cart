package com.briskware.shoppingcart.api

import akka.actor.{ActorLogging, Actor, Props, ActorSystem}
import akka.io.IO
import spray.can.Http
import spray.routing._

import com.briskware.shoppingcart.domain.{Orange, Apple, Product, ShoppingCart}
import com.briskware.shoppingcart.service.CheckoutService

import spray.json._

/**
 * Created by stefan on 28/07/15.
 */

trait Protocols extends DefaultJsonProtocol {
  implicit object ProductJsonFormat extends RootJsonFormat[Product]  {
    override def write(obj: Product): JsValue = obj match {
      case Apple => JsObject("kind" -> JsString("Apple"))
      case Orange => JsObject("kind" -> JsString("Orange"))
    }

    override def read(json: JsValue): Product = json.asJsObject.fields("kind") match {
      case JsString("Apple") => Apple
      case JsString("Orange") => Orange
      case _ => throw new IllegalArgumentException(s"unexpected input ${json}")
    }
  }

  implicit val cartFormat = jsonFormat1(ShoppingCart)

}

trait ApiService extends HttpService with CheckoutService with Protocols {

 import spray.httpx.SprayJsonSupport._

 val routes = {
    pathPrefix("api") {
      path("checkout") {
        post {
          entity(as[ShoppingCart]) { cart =>
            complete {
              checkoutCart(cart).toString
            }
          }
        }
      }
    }
  }

}

class ApiActor extends Actor with ActorLogging with ApiService {
  def actorRefFactory = context

  def receive = runRoute(routes)
}



object Boot extends App {
  implicit lazy val system = ActorSystem("shopping-cart")

  val service = system.actorOf(Props[Booter], "booter")
}

class Booter extends Actor with ActorLogging {

  import Boot.system

  val service = context.actorOf(Props[ApiActor], "api-service")

  override def preStart() {
    IO(Http) ! Http.Bind(service, "localhost", 8080)
  }

  def receive = {
    case akka.io.Tcp.Bound(address) => log.info(s"System is listening to $address")
    case msg @ _ =>
      log.error(s"SHUTTING DOWN!!! Failed to Bind to network interface: ${msg}")
      system.shutdown()
  }
}
