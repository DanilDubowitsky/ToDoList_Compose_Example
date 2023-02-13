package com.example.todolist.data.core.repository

import com.example.todolist.data.core.source.task.IDayTaskLocalSource
import com.example.todolist.domain.model.DayTask
import com.example.todolist.domain.model.sql.DayTaskSQL
import com.example.todolist.domain.repository.IDayTaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged

class DayTaskRepository(
    private val taskLocalSource: IDayTaskLocalSource
) : IDayTaskRepository {

    override suspend fun addTask(dayTask: DayTaskSQL) =
        taskLocalSource.addTask(dayTask)

    override suspend fun getTasksReactive(): Flow<List<DayTask>> =
        taskLocalSource.getTasksReactive().distinctUntilChanged()

    override suspend fun getTask(id: Long): DayTask =
        taskLocalSource.getTask(id)
}
