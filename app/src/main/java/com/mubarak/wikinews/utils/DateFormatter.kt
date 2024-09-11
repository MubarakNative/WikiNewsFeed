package com.mubarak.wikinews.utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter

object DateFormatter {
    private var currentDate: LocalDate = LocalDate.now()
    private var formatter :DateTimeFormatter= DateTimeFormatter.ofPattern("yyyy/MM/dd")
    var formattedDate:String = currentDate.format(formatter)
}