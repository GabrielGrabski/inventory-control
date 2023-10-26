package com.grabas.inventorycontrol.modules.product.repository

import com.grabas.inventorycontrol.modules.product.model.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.*

interface ProductRepository : JpaRepository<Product, Int> {

    @Query("SELECT * FROM PRODUCT", nativeQuery = true)
    override fun findAll(): List<Product>

    @Query("SELECT * FROM PRODUCT WHERE ID = :id", nativeQuery = true)
    override fun findById(@Param("id") id: Int): Optional<Product>
}