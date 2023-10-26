package com.grabas.inventorycontrol.modules.product.model

import jakarta.persistence.*
import java.math.BigDecimal

@Table(name = "PRODUCT")
@Entity(name = "PRODUCT")
data class Product(

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRODUCT_SEQ")
    @SequenceGenerator(name = "PRODUCT_SEQ", sequenceName = "PRODUCT_SEQ", allocationSize = 1)
    val id: Int? = null,

    @Column(name = "NAME")
    val name: String,

    @Column(name = "DESCRIPTION")
    val description: String,

    @Column(name = "PRICE")
    val price: BigDecimal
)