package com.example.todolist.domain.repository

import com.example.todolist.domain.model.DayTask
import com.example.todolist.domain.model.sql.DayTaskSQL
import kotlinx.coroutines.flow.Flow

interface IDayTaskRepository {
    suspend fun addTask(dayTask: DayTaskSQL)
    suspend fun getTasksReactive(): Flow<List<DayTask>>
    suspend fun getTask(id: Long): DayTask
}
