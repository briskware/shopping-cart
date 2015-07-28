package com.briskware.shoppingcart.service

import com.briskware.shoppingcart.domain.{Orange, Apple, ShoppingCart}
import org.scalatest._

/**
 * Created by stefan on 28/07/15.
 */
class CheckoutServiceSpec extends FlatSpec with Matchers with CheckoutService {

  "The CheckoutService" should "return 0 if no products in a cart" in {
    val cart = ShoppingCart(List())

    checkoutCart(cart) should be (0)
  }

  it should "return £1.20 for two apples" in {
    val cart = ShoppingCart(List(Apple,Apple))

    checkoutCart(cart) should be (1.20)
  }

  it should "return £2.05 for 3 apples and one orange" in {
    val cart = ShoppingCart(List(Apple, Apple, Orange, Apple))

    checkoutCart(cart) should be (2.05)
  }

}
