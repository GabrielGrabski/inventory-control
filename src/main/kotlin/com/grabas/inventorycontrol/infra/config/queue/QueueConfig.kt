package com.grabas.inventorycontrol.infra.config.queue

import org.springframework.amqp.core.Queue
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class QueueConfig() {

    @Bean
    fun createImportProductQueue() = Queue("q.product-importer")
}