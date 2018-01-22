package com.oliva.antonio.dornatest.utils

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by antonio on 1/22/18.
 */

fun formatEventDates(start: Long, end: Long): String {
    val dayDateFormat = SimpleDateFormat("dd", Locale.getDefault())
    val monthDateFormat = SimpleDateFormat("MMM", Locale.getDefault())

    val startDay = dayDateFormat.format(start)
    val startMonth = monthDateFormat.format(start)
    val endDay = dayDateFormat.format(end)
    val endMonth = monthDateFormat.format(end)

    return "$startDay $startMonth - $endDay $endMonth"
}