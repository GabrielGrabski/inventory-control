package com.grabas.inventorycontrol

import org.springframework.amqp.rabbit.annotation.EnableRabbit
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@EnableRabbit
@SpringBootApplication
class InventoryControlApplication

fun main(args: Array<String>) {
	runApplication<InventoryControlApplication>(*args)
}
