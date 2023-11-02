package com.grabas.inventorycontrol.modules.category.dto

data class CategoryRequest(
    val id: Int? = null,
    val name: String,
    val productsIds: List<Int> = emptyList()
)