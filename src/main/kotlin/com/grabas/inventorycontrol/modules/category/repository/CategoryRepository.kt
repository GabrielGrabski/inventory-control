package com.grabas.inventorycontrol.modules.category.repository

import com.grabas.inventorycontrol.modules.category.model.Category
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CategoryRepository : JpaRepository<Category, Int>