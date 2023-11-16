package com.grabas.inventorycontrol.modules.product.dto

import com.grabas.inventorycontrol.modules.product.importer.dto.ProductImporterDTO
import java.math.BigDecimal

data class ProductRequest(
    val id: Int?,
    val name: String,
    val description: String,
    val price: BigDecimal,
    val quantity: Int,
    val categoriesIds: ArrayList<Int> = arrayListOf()
) {
    constructor(dto: ProductImporterDTO, categories: ArrayList<Int>) :
            this(null, dto.name!!, dto.description!!, dto.price!!, dto.quantity!!, categories)
}