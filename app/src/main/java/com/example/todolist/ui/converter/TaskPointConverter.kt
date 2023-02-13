package com.example.todolist.ui.converter

import com.example.todolist.domain.model.TaskPoint
import com.example.todolist.ui.model.point.TaskPointUI

fun TaskPoint.toUI() = TaskPointUI(
    id,
    body,
    completed
)

fun List<TaskPoint>.toUIModels() = this.map(TaskPoint::toUI)
