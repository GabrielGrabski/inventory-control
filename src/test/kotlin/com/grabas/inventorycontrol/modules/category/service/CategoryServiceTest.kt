package com.grabas.inventorycontrol.modules.category.service

import com.grabas.inventorycontrol.modules.category.repository.CategoryRepository
import com.grabas.inventorycontrol.modules.product.repository.ProductRepository
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CategoryServiceTest {

    @InjectMocks
    private lateinit var service: CategoryService

    @Mock
    private lateinit var repository: CategoryRepository

    @Mock
    private lateinit var productRepository: ProductRepository

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
    }

    // TODO tests
}