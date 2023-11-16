package com.grabas.inventorycontrol.common.utils

import com.opencsv.bean.CsvToBeanBuilder
import java.io.BufferedReader

class CsvUtils {

    fun <T> csvTo(classToBeReturned: Class<T>, reader: BufferedReader): List<T> = CsvToBeanBuilder<T>(reader)
        .withType(classToBeReturned)
        .build()
        .parse()
}