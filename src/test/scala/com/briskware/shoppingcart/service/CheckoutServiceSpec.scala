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

  it should "return £0.60 for two apples" in {
    val cart = ShoppingCart(List(Apple,Apple))

    checkoutCart(cart) should be (.60)
  }

  it should "return £1.20 for three apples" in {
    val cart = ShoppingCart(List(Apple,Apple,Apple))

    checkoutCart(cart) should be (1.20)
  }

  it should "return £1.20 for four apples" in {
    val cart = ShoppingCart(List(Apple,Apple,Apple,Apple))

    checkoutCart(cart) should be (1.20)
  }

  it should "return £1.80 for five apples" in {
    val cart = ShoppingCart(List(Apple,Apple,Apple,Apple,Apple))

    checkoutCart(cart) should be (1.80)
  }

  it should "return £1.45 for 3 apples and one orange" in {
    val cart = ShoppingCart(List(Apple, Apple, Orange, Apple))

    checkoutCart(cart) should be (1.45)
  }

  it should "return £0.50 for 2 oranges" in {
    val cart = ShoppingCart(List(Orange, Orange))

    checkoutCart(cart) should be (.5)
  }

  it should "return £0.50 for 3 oranges" in {
    val cart = ShoppingCart(List(Orange, Orange, Orange))

    checkoutCart(cart) should be (.5)
  }

  it should "return £0.75 for 4 oranges" in {
    val cart = ShoppingCart(List(Orange, Orange, Orange, Orange))

    checkoutCart(cart) should be (.75)
  }

  it should "return £1.00 for 5 oranges" in {
    val cart = ShoppingCart(List(Orange, Orange, Orange, Orange, Orange))

    checkoutCart(cart) should be (1.0)
  }

  it should "return £1.00 for 6 oranges" in {
    val cart = ShoppingCart(List(Orange, Orange, Orange, Orange, Orange, Orange))

    checkoutCart(cart) should be (1.0)
  }

  it should "return £1.25 for 7 oranges" in {
    val cart = ShoppingCart(List(Orange, Orange, Orange, Orange, Orange, Orange, Orange))

    checkoutCart(cart) should be (1.25)
  }

  it should "return £2.45 for 7 oranges and 3 apples" in {
    val cart = ShoppingCart(List(Orange, Orange, Orange, Orange, Orange, Orange, Orange, Apple, Apple, Apple))

    checkoutCart(cart) should be (2.45)
  }
}
