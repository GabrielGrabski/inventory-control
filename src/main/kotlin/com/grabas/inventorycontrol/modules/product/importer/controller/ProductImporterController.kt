package com.grabas.inventorycontrol.modules.product.importer.controller

import com.grabas.inventorycontrol.modules.product.importer.sender.ProductImporterSender
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("api/products/importer")
class ProductImporterController(@Autowired private val sender: ProductImporterSender) {
    @PostMapping
    fun import(@RequestParam file: MultipartFile) = sender.startProductImportationProcess(file)
}