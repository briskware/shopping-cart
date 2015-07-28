package com.briskware.shoppingcart

/**
 * Created by stefan on 28/07/15.
 */
package object domain {

  /**
   * Created by stefan on 28/07/15.
   */
  trait Product
  case object Apple extends Product
  case object Orange extends Product

  case class ShoppingCart(products: List[Product])

}
