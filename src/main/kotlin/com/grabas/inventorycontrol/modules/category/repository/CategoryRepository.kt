package com.grabas.inventorycontrol.modules.category.repository

import com.grabas.inventorycontrol.modules.category.model.Category
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface CategoryRepository : JpaRepository<Category, Int> {

    @Query(
        value = """
        SELECT 
            * 
        FROM 
            CATEGORY C
        WHERE 
            C.ID IN (:ids)
    """,
        nativeQuery = true
    )
    fun findByIds(@Param("ids") ids: List<Int>): List<Category>
}