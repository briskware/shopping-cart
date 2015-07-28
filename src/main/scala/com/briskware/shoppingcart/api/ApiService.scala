package com.briskware.shoppingcart.api

import akka.actor.{ActorLogging, Actor}
import com.briskware.shoppingcart.domain.ShoppingCart
import com.briskware.shoppingcart.service.CheckoutService
import spray.routing.HttpService

/**
 * Created by stefan on 28/07/15.
 */
trait ApiService extends HttpService with CheckoutService with JsonProtocol {

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

