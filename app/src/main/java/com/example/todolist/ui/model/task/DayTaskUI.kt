package com.example.todolist.ui.model.task

data class DayTaskUI(
    val id: Long,
    val title: String,
    val date: String,
    val completedTasks: Int,
    val totalTasks: Int
)
