package com.grabas.inventorycontrol.exception.model

import java.lang.RuntimeException

class RequiredFieldException(override val message: String) : RuntimeException()