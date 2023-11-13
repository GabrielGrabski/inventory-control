package com.grabas.inventorycontrol.modules.product.controller

import com.grabas.inventorycontrol.modules.product.dto.ProductCategoryAssociationDto
import com.grabas.inventorycontrol.modules.product.dto.ProductRequest
import com.grabas.inventorycontrol.modules.product.dto.ProductResponse
import com.grabas.inventorycontrol.modules.product.service.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/products")
class ProductController(@Autowired val service: ProductService) {

    @PostMapping
    fun create(@RequestBody request: ProductRequest) = service.save(request)

    @PutMapping
    fun update(@RequestBody request: ProductRequest) = service.save(request)

    @PutMapping("associate")
    fun associateProductsToCategories(@RequestBody dto: ProductCategoryAssociationDto) =
        service.associateProductsToCategories(dto)

    @GetMapping("{pageNumber}/{pageSize}")
    fun findAll(@PathVariable pageNumber: Int, @PathVariable pageSize: Int): Page<ProductResponse> =
        service.findAll(pageNumber, pageSize)

    @GetMapping("{id}")
    fun findById(@PathVariable id: Int): ProductResponse = service.findById(id)
}