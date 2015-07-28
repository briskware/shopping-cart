package com.briskware.shoppingcart.service

import com.briskware.shoppingcart.domain._

/**
 * Created by stefan on 28/07/15.
 */
trait ProductPricingService {
  def priceProduct(prod: Product): Option[BigDecimal] = prod match {
    case Apple => Some(BigDecimal(.60D))
    case Orange => Some(BigDecimal(.25D))
    case _ => None
  }
}
