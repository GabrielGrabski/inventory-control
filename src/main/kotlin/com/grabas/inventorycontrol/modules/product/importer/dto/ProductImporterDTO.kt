package com.grabas.inventorycontrol.modules.product.importer.dto

import com.opencsv.bean.CsvBindByName
import java.math.BigDecimal

data class ProductImporterDTO(
    @CsvBindByName(column = "Name")
    val name: String? = null,
    @CsvBindByName(column = "Description")
    val description: String? = null,
    @CsvBindByName(column = "Price")
    val price: BigDecimal? = null,
    @CsvBindByName(column = "Quantity")
    val quantity: Int? = null,
    @CsvBindByName(column = "Categories")
    val categories: String? = null
)