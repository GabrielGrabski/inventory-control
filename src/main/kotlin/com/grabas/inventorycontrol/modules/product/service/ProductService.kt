package com.grabas.inventorycontrol.modules.product.service

import com.grabas.inventorycontrol.exception.enums.ErrorMessages
import com.grabas.inventorycontrol.exception.model.NotFoundException
import com.grabas.inventorycontrol.exception.model.RequiredFieldException
import com.grabas.inventorycontrol.modules.product.dto.ProductRequest
import com.grabas.inventorycontrol.modules.product.dto.ProductResponse
import com.grabas.inventorycontrol.modules.product.model.Product
import com.grabas.inventorycontrol.modules.product.repository.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.lang.RuntimeException
import java.lang.String.format
import java.math.BigDecimal

@Service
class ProductService(@Autowired val repository: ProductRepository) {

    fun save(request: ProductRequest) {
        validateProduct(request)
        repository.save(Product(
            name = request.name,
            description = request.description,
            price = request.price
        ))
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

    private fun validateProduct(request: ProductRequest) {
        validateEmptyOrNullFields(request)
    }

    private fun validateEmptyOrNullFields(request: ProductRequest) {
        if (request.name.isEmpty()) throw RequiredFieldException(ErrorMessages.NAME_REQUIRED.message)
        if (request.description.isEmpty()) throw RequiredFieldException(ErrorMessages.DESCRIPTION_REQUIRED.message)
        if (request.price == BigDecimal.ZERO) throw RequiredFieldException(ErrorMessages.PRICE_SHOULD_NOT_BE_ZERO.message)
    }
}