package com.grabas.inventorycontrol.exception.model

import java.lang.RuntimeException

class NotFoundException(override val message: String) : RuntimeException()