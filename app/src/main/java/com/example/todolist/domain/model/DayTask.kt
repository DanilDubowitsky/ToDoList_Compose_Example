package com.example.todolist.domain.model

data class DayTask(
    val id: Long,
    val title: String,
    val date: Long,
    val completedTasksCount: Int,
    val totalTasksCount: Int,
    val incompleteTaskCount: Int
)
