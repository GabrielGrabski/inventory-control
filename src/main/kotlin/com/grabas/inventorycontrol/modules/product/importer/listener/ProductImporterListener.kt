package com.grabas.inventorycontrol.modules.product.importer.listener

import com.grabas.inventorycontrol.modules.product.importer.service.ProductImporterService
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.nio.file.Paths

@Component
class ProductImporterListener(
    @Autowired
    private val service: ProductImporterService
) {

    @RabbitListener(queues = ["q.product-importer"])
    fun importProduct(path: String) {
        service.import(Paths.get(path).toFile())
    }
}