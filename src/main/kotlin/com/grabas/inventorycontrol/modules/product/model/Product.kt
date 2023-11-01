package com.grabas.inventorycontrol.modules.product.model

import com.grabas.inventorycontrol.modules.category.model.Category
import jakarta.persistence.*
import java.math.BigDecimal

@Table(name = "PRODUCT")
@Entity(name = "PRODUCT")
data class Product(

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PRODUCT")
    @SequenceGenerator(name = "SEQ_PRODUCT", sequenceName = "SEQ_PRODUCT", allocationSize = 1)
    val id: Int? = null,

    @Column(name = "NAME")
    val name: String,

    @Column(name = "DESCRIPTION")
    val description: String,

    @Column(name = "PRICE")
    val price: BigDecimal,

    @Column(name = "QUANTITY")
    val quantity: Int,

    @ManyToMany
    @JoinTable(
        name = "PRODUCT_CATEGORY",
        joinColumns = [JoinColumn(name = "PRODUCT_ID")],
        inverseJoinColumns = [JoinColumn(name = "CATEGORY_ID")]
    )
    val categories: List<Category>
)