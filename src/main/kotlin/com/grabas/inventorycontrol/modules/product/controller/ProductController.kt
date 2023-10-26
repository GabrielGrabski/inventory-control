package com.grabas.inventorycontrol.modules.product.controller

import com.grabas.inventorycontrol.modules.product.dto.ProductRequest
import com.grabas.inventorycontrol.modules.product.dto.ProductResponse
import com.grabas.inventorycontrol.modules.product.model.Product
import com.grabas.inventorycontrol.modules.product.service.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/products")
class ProductController(@Autowired val service: ProductService) {

    @PostMapping
    fun save(@RequestBody request: ProductRequest) {
        service.save(request)
    }

    @GetMapping
    fun findAll(): List<ProductResponse> {
        return service.findAll()
    }

    @GetMapping("{id}")
    fun findById(@PathVariable id: Int): ProductResponse {
        return service.findById(id)
    }
}