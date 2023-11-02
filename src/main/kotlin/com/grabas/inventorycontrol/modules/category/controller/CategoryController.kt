package com.grabas.inventorycontrol.modules.category.controller

import com.grabas.inventorycontrol.modules.category.dto.CategoryRequest
import com.grabas.inventorycontrol.modules.category.dto.CategoryResponse
import com.grabas.inventorycontrol.modules.category.service.CategoryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/categories")
class CategoryController(@Autowired val service: CategoryService) {

    @GetMapping
    fun findAll(): List<CategoryResponse> {
        return service.findAll()
    }

    @GetMapping("{id}")
    fun findById(@PathVariable id: Int): CategoryResponse {
        return service.findById(id)
    }

    @PostMapping
    fun create(@RequestBody request: CategoryRequest) {
        service.save(request)
    }

    @PutMapping
    fun update(request: CategoryRequest) {
        service.save(request)
    }
}