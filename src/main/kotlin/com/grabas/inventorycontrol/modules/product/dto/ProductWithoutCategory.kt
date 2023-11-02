package com.grabas.inventorycontrol.modules.product.dto

import java.math.BigDecimal

data class ProductWithoutCategory(
    val id: Int,
    val name: String,
    val description: String,
    val price: BigDecimal,
    val quantity: Int
)