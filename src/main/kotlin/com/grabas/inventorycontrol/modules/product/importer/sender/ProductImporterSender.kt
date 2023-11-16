package com.grabas.inventorycontrol.modules.product.importer.sender

import org.apache.commons.io.FileUtils
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.time.LocalDateTime
import kotlin.time.Duration.Companion.seconds

@Component
class ProductImporterSender(@Autowired private val rabbit: RabbitTemplate) {

    fun startProductImportationProcess(file: MultipartFile) {
        val tempFile: File = File.createTempFile(LocalDateTime.now().second.seconds.toString(), ".csv")
        FileUtils.copyInputStreamToFile(file.inputStream, tempFile)
        rabbit.convertAndSend("q.product-importer", tempFile.path)
    }
}