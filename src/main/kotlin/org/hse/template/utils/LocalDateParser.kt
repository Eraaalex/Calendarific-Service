package org.hse.template.utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

fun String.parseDate(): LocalDate? {
    val dateFormatters = listOf(
        DateTimeFormatter.ISO_OFFSET_DATE_TIME, // for datetime strings with offset
        DateTimeFormatter.ISO_LOCAL_DATE // for date strings without time
    )

    for (formatter in dateFormatters) {
        try {
            if (formatter == DateTimeFormatter.ISO_OFFSET_DATE_TIME) {
                return LocalDate.parse(this, formatter)
            } else {
                return LocalDate.parse(this.substring(0, 10), formatter)
            }
        } catch (e: DateTimeParseException) {

        }
    }

    return null
}