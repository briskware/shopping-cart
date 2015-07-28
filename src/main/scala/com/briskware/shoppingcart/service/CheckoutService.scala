package com.briskware.shoppingcart.service

import com.briskware.shoppingcart.domain.ShoppingCart

/**
 * Created by stefan on 28/07/15.
 */

trait CheckoutService extends ProductPricingService {

  def checkoutCart(cart: ShoppingCart): BigDecimal = {
    cart.products.flatMap( priceProduct(_)).foldLeft(BigDecimal(0))( _ + _ )
  }

}
