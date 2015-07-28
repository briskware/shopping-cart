package com.briskware.shoppingcart.api

import akka.actor.{ActorLogging, Actor, Props, ActorSystem}
import akka.io.IO
import spray.can.Http

/**
 * Created by stefan on 28/07/15.
 */
class Booter extends Actor with ActorLogging {

  import Boot.system

  val service = context.actorOf(Props[ApiActor], "api-service")

  override def preStart() {
    implicit val system = context.system
    IO(Http) ! Http.Bind(service, "localhost", 8080)
  }

  def receive = {
    case akka.io.Tcp.Bound(address) => log.info(s"System is listening to $address")
    case msg @ _ =>
      log.error(s"SHUTTING DOWN!!! Failed to Bind to network interface: ${msg}")
      system.shutdown()
  }
}

object Boot extends App {
  implicit lazy val system = ActorSystem("shopping-cart")

  val service = system.actorOf(Props[Booter], "booter")
}

