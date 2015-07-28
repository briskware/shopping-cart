package com.briskware.shoppingcart.api

import com.briskware.shoppingcart.domain.{ShoppingCart, Orange, Apple, Product}
import spray.json._

/**
 * Created by stefan on 28/07/15.
 */
trait JsonProtocol extends DefaultJsonProtocol {
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

