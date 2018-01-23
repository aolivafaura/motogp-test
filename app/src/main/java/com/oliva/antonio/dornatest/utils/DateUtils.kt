package com.oliva.antonio.dornatest.utils

import android.util.Log
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

fun formatSessionDates(start: Long, end: Long): String {
    Log.d("FORMAT", start.toString() + " " + end)
    val startDateFormat = SimpleDateFormat("EEEE dd MMMM hh:mm", Locale.getDefault())
    val endDateFormat =  SimpleDateFormat("hh:mm", Locale.getDefault())

    val startDate = startDateFormat.format(start)
    val endDate = endDateFormat.format(end)

    return "$startDate - $endDate"
}