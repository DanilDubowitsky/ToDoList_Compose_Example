package com.example.todolist.ui.viewmodel.overview

import com.example.todolist.ui.model.point.TaskPointUI

data class TaskOverviewState(
    val taskTitle: String = "",
    val date: String = "",
    val completeTaskPoints: List<TaskPointUI> = emptyList(),
    val incompleteTaskPoints: List<TaskPointUI> = emptyList()
)
