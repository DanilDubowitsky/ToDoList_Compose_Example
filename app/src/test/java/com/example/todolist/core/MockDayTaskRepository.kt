package com.example.todolist.core

import com.example.todolist.domain.model.DayTask
import com.example.todolist.domain.model.sql.DayTaskSQL
import com.example.todolist.domain.repository.IDayTaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class MockDayTaskRepository : IDayTaskRepository {

    private val mockTaskData = listOf(
        DayTask(
            1,
            "Default Task",
            2222,
            0,
            2,
            2
        ),
        DayTask(
            2,
            "Default Task2",
            2222,
            0,
            2,
            2
        )
    )

    override suspend fun addTask(dayTask: DayTaskSQL) {}

    override suspend fun getTasksReactive(): Flow<List<DayTask>> = flowOf(mockTaskData)

    override suspend fun getTask(id: Long): DayTask = DayTask(
        1,
        "Default Task",
        2222,
        0,
        2,
        2
    )
}
