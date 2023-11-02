package com.grabas.inventorycontrol.modules.category.dto

import com.grabas.inventorycontrol.modules.product.dto.ProductWithoutCategory

data class CategoryResponse(
    val id: Int,
    val name: String,
    val products: List<ProductWithoutCategory>
)