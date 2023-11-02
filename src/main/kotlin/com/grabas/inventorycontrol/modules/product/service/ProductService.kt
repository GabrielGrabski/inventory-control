package com.grabas.inventorycontrol.modules.product.service

import com.grabas.inventorycontrol.exception.enums.ErrorMessages
import com.grabas.inventorycontrol.exception.model.NotFoundException
import com.grabas.inventorycontrol.exception.model.RequiredFieldException
import com.grabas.inventorycontrol.modules.category.model.Category
import com.grabas.inventorycontrol.modules.category.service.CategoryService
import com.grabas.inventorycontrol.modules.product.dto.ProductCategoryAssociationDto
import com.grabas.inventorycontrol.modules.product.dto.ProductRequest
import com.grabas.inventorycontrol.modules.product.dto.ProductResponse
import com.grabas.inventorycontrol.modules.product.model.Product
import com.grabas.inventorycontrol.modules.product.repository.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.lang.String.format
import java.math.BigDecimal

@Service
class ProductService(
    @Autowired private val repository: ProductRepository,
    @Autowired private val categoryService: CategoryService
) {

    fun save(request: ProductRequest) {
        val categories = categoryService.findByIdsOrThrow(request.categoriesIds)
        validateProduct(request)
        repository.save(Product(request, categories.toMutableList()))
    }

    fun findAll(): List<ProductResponse> {
        return repository.findAll()
            .map { ProductResponse.from(it) }
            .toCollection(mutableListOf())
    }

    fun findById(id: Int): ProductResponse {
        val product = repository.findById(id)
            .orElseThrow { NotFoundException(format(ErrorMessages.PRODUCT_NOT_FOUND.message, id)) }
        return ProductResponse.from(product)
    }

    fun associateProductsToCategories(dto: ProductCategoryAssociationDto) {
        val categories: List<Category> = categoryService.findByIds(dto.categoriesIds)
        val products: List<Product> = repository.findAllById(dto.productsIds)

        associateCategoryToProductAndSave(products, categories)
        categoryService.associateProductToCategoryAndSave(categories, products)
    }

    private fun associateCategoryToProductAndSave(products: List<Product>, categories: List<Category>) {
        products.forEach { it.categories.addAll(categories) }
        repository.saveAll(products)
    }

    private fun validateProduct(request: ProductRequest) {
        validateEmptyFields(request)
        validatePriceEqualsZero(request)
    }

    private fun validateEmptyFields(request: ProductRequest) {
        if (request.name.isEmpty()) throw RequiredFieldException(ErrorMessages.NAME_REQUIRED.message)
        if (request.description.isEmpty()) throw RequiredFieldException(ErrorMessages.DESCRIPTION_REQUIRED.message)
    }

    private fun validatePriceEqualsZero(request: ProductRequest) {
        if (request.price == BigDecimal.ZERO) throw RequiredFieldException(ErrorMessages.PRICE_SHOULD_NOT_BE_ZERO.message)
    }
}