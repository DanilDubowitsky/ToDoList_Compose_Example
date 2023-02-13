package com.example.todolist.data.entity.point

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TaskPointEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val taskId: Long,
    val completed: Boolean,
    val body: String
)
