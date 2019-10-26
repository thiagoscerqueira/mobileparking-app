package br.com.enterprise.mobileparking.app.util

import java.time.Duration
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

object DateTimeUtil {

    fun formataDurationHHmmss(duration: Duration?): String? {
        return duration?.let { LocalTime.MIDNIGHT.plus(duration).format(DateTimeFormatter.ofPattern("HH:mm:ss")) }
    }

    fun formataDateTime(dateTime: LocalDateTime?): String? {
        return dateTime?.format(DateTimeFormatter.ofPattern("dd/MM/YYYY HH:mm:ss"))
    }


}