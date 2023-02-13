package com.example.todolist.ui.utils

import java.text.SimpleDateFormat
import java.util.Locale

const val TASK_DATE_TIME_FORMAT = "dd MMMM yyyy"

fun formatMillisToDisplayDate(
    date: Long,
    format: String,
    locale: Locale = Locale.getDefault()
): String = SimpleDateFormat(format, locale).format(date)
