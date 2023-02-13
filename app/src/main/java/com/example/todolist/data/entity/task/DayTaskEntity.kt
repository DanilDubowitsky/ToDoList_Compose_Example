package com.example.todolist.data.entity.task

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DayTaskEntity(
    @PrimaryKey
    val id: Long,
    val date: Long,
    val title: String
)
