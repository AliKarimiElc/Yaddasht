package com.example.yaddasht.utilities

import com.github.mfathi91.time.PersianDate
import java.text.SimpleDateFormat
import java.util.*

class DateTimeHelper {

    companion object {

        private var persianDate: PersianDate? = null
        private const val dateTimeFormat = "yyyy/MM/dd HH:mm:ss"

        fun getCurrentDateTimeStringJalali(): String {
            val c = PersianDate.now()
            val t = Calendar.getInstance().time
            return "${c.year}/${c.monthValue}/${c.dayOfMonth} ${t.hours}:${t.minutes}:${t.seconds}"
        }

        private fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
            val formatter = SimpleDateFormat(format, locale)
            return formatter.format(this)
        }
    }
}