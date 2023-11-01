package com.grabas.inventorycontrol.modules.category.service

import com.grabas.inventorycontrol.exception.enums.ErrorMessages
import com.grabas.inventorycontrol.exception.model.NotFoundException
import com.grabas.inventorycontrol.modules.category.model.Category
import com.grabas.inventorycontrol.modules.category.repository.CategoryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CategoryService(@Autowired private val repository: CategoryRepository) {

    fun findByIdsOrThrow(ids: List<Int>): List<Category> {
        val categories = repository.findByIds(ids)
        validateEmptyCategories(categories)
        return categories
    }

    private fun validateEmptyCategories(categories: List<Category>) {
        if (categories.isEmpty()) throw NotFoundException(ErrorMessages.CATEGORIES_NOT_FOUND.message);
    }
}