package com.grabas.inventorycontrol.modules.product.service

import com.grabas.inventorycontrol.exception.enums.ErrorMessages
import com.grabas.inventorycontrol.exception.model.NotFoundException
import com.grabas.inventorycontrol.exception.model.RequiredFieldException
import com.grabas.inventorycontrol.modules.product.dto.ProductRequest
import com.grabas.inventorycontrol.modules.product.dto.ProductResponse
import com.grabas.inventorycontrol.modules.product.model.Product
import com.grabas.inventorycontrol.modules.product.repository.ProductRepository
import org.assertj.core.api.Assertions.*
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
import java.math.BigDecimal
import java.util.*

@RunWith(MockitoJUnitRunner::class)
@ExtendWith(SpringExtension::class)
class ProductServiceTest {

    @InjectMocks
    private lateinit var service: ProductService

    @Mock
    private lateinit var repository: ProductRepository

    companion object {
        const val EMPTY: String = ""
    }

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun save_shouldThrowError_whenNameIsEmpty() {
        assertThatExceptionOfType(RequiredFieldException::class.java)
            .isThrownBy { service.save(ProductRequest(EMPTY, "Description test", BigDecimal.ONE)) }
            .withMessage(ErrorMessages.NAME_REQUIRED.message)
    }

    @Test
    fun save_shouldThrowError_whenDescriptionIsEmpty() {
        assertThatExceptionOfType(RequiredFieldException::class.java)
            .isThrownBy { service.save(ProductRequest("Name test", EMPTY, BigDecimal.ONE)) }
            .withMessage(ErrorMessages.DESCRIPTION_REQUIRED.message)
    }

    @Test
    fun save_shouldThrowError_whenPriceIsZero() {
        assertThatExceptionOfType(RequiredFieldException::class.java)
            .isThrownBy { service.save(ProductRequest("Name test", "Description", BigDecimal.ZERO)) }
            .withMessage(ErrorMessages.PRICE_SHOULD_NOT_BE_ZERO.message)
    }

    @Test
    fun save_shouldSave_whenAllFieldsArePresent() {
        assertThatCode { service.save(ProductRequest("Name", "Desc", BigDecimal.ONE)) }
            .doesNotThrowAnyException()

        verify(repository, times(1)).save(any())
    }

    @Test
    fun findById_shouldReturnEntity_whenHasDataInDB() {
        `when`(repository.findById(anyInt()))
            .thenReturn(Optional.of(Product(1, "Name", "Desc", BigDecimal.TEN)))
        assertThat(service.findById(1))
            .isEqualTo(ProductResponse(1, "Name", "Desc", BigDecimal.TEN))
    }

    @Test
    fun findById_shouldThrowNotFoundException_whenHasNoDataInDB() {
        `when`(repository.findById(anyInt())).thenReturn(Optional.empty())
        assertThatExceptionOfType(NotFoundException::class.java).isThrownBy { service.findById(1) }
    }

    @Test
    fun findAll_shouldReturnEmptyList_whenHasNoDataInDB() {
        `when`(repository.findAll()).thenReturn(emptyList())
        assertThat(service.findAll()).isEmpty()
    }

    @Test
    fun findAll_shouldReturnPopulatedList_whenHasDataInDb() {
        `when`(repository.findAll()).thenReturn(
            listOf(
                Product(1, "Name", "Desc", BigDecimal.TEN),
                Product(2, "Product 2", "Desc 2", BigDecimal.valueOf(200)),
            )
        )

        assertThat(service.findAll()).hasSize(2)
    }
}