package com.grabas.inventorycontrol.exception.controller

import com.grabas.inventorycontrol.exception.dto.ErrorResponse
import com.grabas.inventorycontrol.exception.enums.ErrorMessages
import com.grabas.inventorycontrol.exception.model.NotFoundException
import com.grabas.inventorycontrol.exception.model.RequiredFieldException
import org.apache.logging.log4j.kotlin.Logging
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionController : Logging {

    @ExceptionHandler
    fun handleNotFoundException(ex: NotFoundException): ErrorResponse {
        return ErrorResponse(ex.message, 500)
    }

    @ExceptionHandler
    fun handleRequiredFieldException(ex: RequiredFieldException): ErrorResponse {
        return ErrorResponse(ex.message, 500)
    }

    @ExceptionHandler
    fun handleGenericException(ex: Exception): ErrorResponse {
        logger.error(ex.message ?: "")
        return ErrorResponse(ErrorMessages.GENERIC_ERROR.message, 500)
    }
}