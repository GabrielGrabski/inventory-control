package com.grabas.inventorycontrol.exception.enums

enum class ErrorMessages(val message: String) {

    PRODUCT_NOT_FOUND("Product with id %s not found."),
    CATEGORIES_NOT_FOUND("Informed categories not found."),
    NAME_REQUIRED("Name is required."),
    DESCRIPTION_REQUIRED("Description is required!"),
    PRICE_SHOULD_NOT_BE_ZERO("Price should not be zero!"),
    GENERIC_ERROR("An error occurred. Contact Support.")
}