package com.example.todolist.ui.converter

import com.example.todolist.domain.model.DayTask
import com.example.todolist.ui.model.task.DayTaskUI
import com.example.todolist.ui.utils.TASK_DATE_TIME_FORMAT
import com.example.todolist.ui.utils.formatMillisToDisplayDate

fun DayTask.toUI() = DayTaskUI(
    id,
    title,
    formatMillisToDisplayDate(date, TASK_DATE_TIME_FORMAT),
    completedTasksCount,
    totalTasksCount
)

fun List<DayTask>.toUIModels() = this.map(DayTask::toUI)
