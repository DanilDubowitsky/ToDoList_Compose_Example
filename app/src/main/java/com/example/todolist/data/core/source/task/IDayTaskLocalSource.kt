package com.example.todolist.data.core.source.task

import com.example.todolist.domain.model.DayTask
import com.example.todolist.domain.model.sql.DayTaskSQL
import kotlinx.coroutines.flow.Flow

interface IDayTaskLocalSource {
    suspend fun addTask(dayTask: DayTaskSQL)
    suspend fun getTasksReactive(): Flow<List<DayTask>>
    suspend fun getTask(id: Long): DayTask
}
