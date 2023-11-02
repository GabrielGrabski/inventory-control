package com.grabas.inventorycontrol.modules.category.service

import com.grabas.inventorycontrol.exception.enums.ErrorMessages
import com.grabas.inventorycontrol.exception.model.NotFoundException
import com.grabas.inventorycontrol.exception.model.RequiredFieldException
import com.grabas.inventorycontrol.modules.category.dto.CategoryRequest
import com.grabas.inventorycontrol.modules.category.dto.CategoryResponse
import com.grabas.inventorycontrol.modules.category.model.Category
import com.grabas.inventorycontrol.modules.category.repository.CategoryRepository
import com.grabas.inventorycontrol.modules.product.dto.ProductWithoutCategory
import com.grabas.inventorycontrol.modules.product.model.Product
import com.grabas.inventorycontrol.modules.product.repository.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CategoryService(
    @Autowired
    private val repository: CategoryRepository,
    @Autowired
    private val productRepository: ProductRepository
) {

    fun save(request: CategoryRequest) {
        val products: List<Product> = productRepository.findAllById(request.productsIds)
        validateRequest(request)
        repository.save(Category(request.id, request.name, products.toMutableList()))
    }

    fun findById(id: Int): CategoryResponse {
        val category = findByIdOrThrow(id)
        return CategoryResponse(category.id!!, category.name, getProductResponse(category.products))
    }

    fun findAll(): List<CategoryResponse> {
        return repository.findAll().map { CategoryResponse(it.id!!, it.name, getProductResponse(it.products)) }
    }

    fun associateProductToCategoryAndSave(categories: List<Category>, products: List<Product>) {
        categories.forEach { it.products.addAll(products) }
        repository.saveAll(categories)
    }

    fun findByIdsOrThrow(ids: List<Int>): List<Category> {
        val categories = repository.findAllById(ids)
        validateEmptyCategories(categories)
        return categories
    }

    fun findByIds(ids: List<Int>): List<Category> {
        return repository.findAllById(ids)
    }

    fun findByIdOrThrow(id: Int): Category {
        return repository.findById(id).orElseThrow { NotFoundException(ErrorMessages.CATEGORY_NOT_FOUND.message) }
    }

    private fun validateEmptyCategories(categories: List<Category>) {
        if (categories.isEmpty()) throw NotFoundException(ErrorMessages.CATEGORIES_NOT_FOUND.message);
    }

    private fun validateRequest(request: CategoryRequest) {
        validateEmptyName(request)
    }

    private fun validateEmptyName(request: CategoryRequest) {
        if (request.name.isBlank()) throw RequiredFieldException(ErrorMessages.NAME_REQUIRED.message)
    }

    private fun getProductResponse(products: List<Product>): List<ProductWithoutCategory> {
        return products.map { ProductWithoutCategory(it.id!!, it.name, it.description, it.price, it.quantity) }
    }
}