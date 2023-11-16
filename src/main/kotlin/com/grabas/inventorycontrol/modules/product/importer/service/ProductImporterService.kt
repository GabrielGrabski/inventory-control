package com.grabas.inventorycontrol.modules.product.importer.service

import com.fasterxml.jackson.core.io.NumberInput
import com.grabas.inventorycontrol.common.utils.CsvUtils
import com.grabas.inventorycontrol.modules.product.dto.ProductRequest
import com.grabas.inventorycontrol.modules.product.importer.dto.ProductImporterDTO
import com.grabas.inventorycontrol.modules.product.service.ProductService
import org.apache.logging.log4j.kotlin.Logging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader

@Service
class ProductImporterService(
    @Autowired
    private val service: ProductService
) : Logging {

    val csvUtils = CsvUtils()

    fun import(file: File) {
        var reader: BufferedReader? = null
        try {
            reader = BufferedReader(InputStreamReader(file.inputStream()))
            saveAllProducts(csvUtils.csvTo(ProductImporterDTO::class.java, reader))
        } catch (ex: Exception) {
            logger.error("Error to convert product: " + ex.message)
        } finally {
            reader?.close()
        }
    }

    private fun extractCategories(categories: String): ArrayList<Int> {
        val categoriesIds: ArrayList<Int> = arrayListOf()
        for (id in categories.split(";")) {
            categoriesIds.add(NumberInput.parseInt(id))
        }
        return categoriesIds
    }

    private fun saveAllProducts(products: List<ProductImporterDTO>) {
        products.forEach { service.save(ProductRequest(it, extractCategories(it.categories!!))) }
    }
}