package com.example.todolist.ui.viewmodel.creation

import com.example.todolist.ui.model.point.TaskPointUI

data class TaskCreationState(
    val taskPoints: List<TaskPointUI> = emptyList()
)
