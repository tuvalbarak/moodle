package com.example.moodle.extensions

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Date?.toDateDayMonthAndYear():String =
    this?.let {
        SimpleDateFormat("dd MMM, yyy", Locale.ROOT).format(it.time) ?: ""
    } ?: ""

