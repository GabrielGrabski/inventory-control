package com.grabas.inventorycontrol.modules.category.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.grabas.inventorycontrol.modules.product.model.Product
import jakarta.persistence.*

@Table(name = "CATEGORY")
@Entity(name = "CATEGORY")
data class Category(

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CATEGORY")
    @SequenceGenerator(name = "SEQ_CATEGORY", sequenceName = "SEQ_CATEGORY", allocationSize = 1)
    val id: Int? = null,

    @Column(name = "NAME", unique = true)
    val name: String,

    @ManyToMany(mappedBy = "categories")
    val products: MutableList<Product>
)