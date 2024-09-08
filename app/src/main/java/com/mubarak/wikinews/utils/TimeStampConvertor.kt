package com.mubarak.wikinews.utils

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

object TimeStampConvertor {
    fun formatTimestampToUtc(isoTimestamp: String): String {
        // Parse the ISO 8601 timestamp
        val instant = Instant.parse(isoTimestamp)

        // Define the formatter for the desired format
        val formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy")
            .withZone(ZoneId.of("UTC"))
        //,'at 'h:mm:ss a

        // Format the instant to the desired format
        return formatter.format(instant)
    }
}