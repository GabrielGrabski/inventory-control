package com.grabas.inventorycontrol.modules.category.service

import com.grabas.inventorycontrol.exception.enums.ErrorMessages
import com.grabas.inventorycontrol.exception.model.NotFoundException
import com.grabas.inventorycontrol.exception.model.RequiredFieldException
import com.grabas.inventorycontrol.modules.category.dto.CategoryRequest
import com.grabas.inventorycontrol.modules.category.dto.CategoryResponse
import com.grabas.inventorycontrol.modules.category.model.Category
import com.grabas.inventorycontrol.modules.category.repository.CategoryRepository
import com.grabas.inventorycontrol.modules.product.repository.ProductRepository
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.Before
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.util.*

@RunWith(MockitoJUnitRunner::class)
@ExtendWith(SpringExtension::class)
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

    @Test
    fun save_shouldSaveEntity_whenCorrectRequest() {
        `when`(productRepository.findAllById(anyList())).thenReturn(listOf())
        service.save(CategoryRequest(null, "Category Test"))
        verify(repository, times(1)).save(any())
    }

    @Test
    fun save_shouldUpdateEntity_whenRequestHaveId() {
        `when`(productRepository.findAllById(anyList())).thenReturn(listOf())
        service.save(CategoryRequest(1, "Category Test"))
        verify(repository, times(1)).save(any())
    }

    @Test
    fun save_shouldThrowEx_whenNameIsEmpty() {
        assertThatExceptionOfType(RequiredFieldException::class.java)
            .isThrownBy { service.save(CategoryRequest(1, "    ")) }
            .withMessage(ErrorMessages.NAME_REQUIRED.message)
    }

    @Test
    fun findById_shouldReturnEntity_whenIdExists() {
        `when`(repository.findById(anyInt())).thenReturn(Optional.of(Category(1, "Categoria", mutableListOf())))
        assertThat(service.findById(1))
            .isEqualTo(CategoryResponse(1, "Categoria", listOf()))
    }

    @Test
    fun findById_shouldThrowEx_whenIdNotExists() {
        `when`(repository.findById(anyInt())).thenReturn(Optional.empty())
        assertThatExceptionOfType(NotFoundException::class.java)
            .isThrownBy { service.findById(1) }
            .withMessage(ErrorMessages.CATEGORY_NOT_FOUND.message)
    }

    @Test
    fun findAll_shoudReturnEmptyList_whenNotExists() {
        `when`(repository.findAll()).thenReturn(emptyList())
        assertThat(service.findAll()).isEmpty()
    }

    @Test
    fun findAll_shoudReturnList_whenExists() {
        `when`(repository.findAll()).thenReturn(
            mutableListOf(
                Category(1, "C1", mutableListOf()),
                Category(2, "C2", mutableListOf()),
            )
        )
        assertThat(service.findAll())
            .hasSize(2)
            .containsExactly(
                CategoryResponse(1, "C1", mutableListOf()),
                CategoryResponse(2, "C2", mutableListOf()),
            )
    }

    @Test
    fun findByIds_shoudReturnEmptyList_whenNotExists() {
        `when`(repository.findAllById(anyList())).thenReturn(emptyList())
        assertThat(service.findByIds(listOf(1, 2))).isEmpty()
    }

    @Test
    fun findByIds_shoudReturnList_whenExists() {
        `when`(repository.findAllById(anyList())).thenReturn(
            mutableListOf(
                Category(1, "C1", mutableListOf()),
                Category(2, "C2", mutableListOf()),
            )
        )
        assertThat(service.findByIds(listOf(1, 2)))
            .hasSize(2)
            .containsExactly(
                Category(1, "C1", mutableListOf()),
                Category(2, "C2", mutableListOf()),
            )
    }
}