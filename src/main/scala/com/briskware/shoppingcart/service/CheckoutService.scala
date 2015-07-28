package com.briskware.shoppingcart.service

import com.briskware.shoppingcart.domain.{Orange, Apple, ShoppingCart, Product}

/**
 * Created by stefan on 28/07/15.
 */
trait CheckoutService extends ProductPricingService with Offers {

  def checkoutCart(cart: ShoppingCart): BigDecimal = {
    //cart.products.flatMap( priceProduct(_)).foldLeft(BigDecimal(0))( _ + _ )

    val groups = cart.products.groupBy(x => x).mapValues(_.length)
    groups.foldLeft(BigDecimal(0)) {  (acc: BigDecimal, pair: (Product, Int)) => pair match {
      case (Apple, units: Int) => acc + priceProduct(Apple).map(buyOneGetOneFree(_, units)).getOrElse(0)
      case (Orange, units: Int) => acc + priceProduct(Orange).map(threeForThePriceOfTwo(_, units)).getOrElse(0)
      case _ => acc
    }}
  }

}

trait Offers {

  def buyOneGetOneFree(unitPrice: BigDecimal, units: Int): BigDecimal = {
    val pairs: Int = units/2
    val remainder: Int = units%2
    unitPrice * (pairs + remainder)
  }

  def threeForThePriceOfTwo(unitPrice: BigDecimal, units: Int): BigDecimal = {
    val triples: Int = units/3
    val remainder: Int = units%3
    unitPrice * (triples*2 + remainder)
  }

}
