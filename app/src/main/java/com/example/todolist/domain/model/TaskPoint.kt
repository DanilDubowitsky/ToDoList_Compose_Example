package com.example.todolist.domain.model

data class TaskPoint(
    val id: Long,
    val taskId: Long,
    val body: String,
    val completed: Boolean
)
