package com.grabas.inventorycontrol.modules.product.dto

import com.grabas.inventorycontrol.modules.product.model.Product
import java.math.BigDecimal

data class ProductResponse(val id: Int, val name: String, val description: String, val price: BigDecimal) {

    companion object {
        fun from(product: Product): ProductResponse =
            ProductResponse(
                product.id!!,
                product.name.lowercase().replaceFirstChar { it.titlecase() },
                product.description.lowercase().replaceFirstChar { it.titlecase() },
                product.price
            )
    }
}