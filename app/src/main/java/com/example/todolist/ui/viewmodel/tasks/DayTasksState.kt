package com.example.todolist.ui.viewmodel.tasks

import com.example.todolist.ui.model.task.DayTaskUI

data class DayTasksState(
    val dayTasks: List<DayTaskUI> = emptyList(),
    val isLoading: Boolean = false
)