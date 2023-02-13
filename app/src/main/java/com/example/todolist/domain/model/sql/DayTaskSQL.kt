package com.example.todolist.domain.model.sql

data class DayTaskSQL(
    val id: Long,
    val date: Long,
    val title: String,
    val points: List<TaskPointSQL>
)
