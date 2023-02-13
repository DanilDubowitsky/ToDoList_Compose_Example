package com.example.todolist.data.core.source.task

import com.example.todolist.data.converters.toModel
import com.example.todolist.data.converters.toModels
import com.example.todolist.data.dao.DayTaskDao
import com.example.todolist.data.entity.task.compound.DayTaskCompound
import com.example.todolist.domain.model.DayTask
import com.example.todolist.domain.model.sql.DayTaskSQL
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DayTaskLocalSource(
    private val dayTaskDao: DayTaskDao
) : IDayTaskLocalSource {

    override suspend fun addTask(dayTask: DayTaskSQL) =
        dayTaskDao.insertOrUpdate(dayTask)

    override suspend fun getTasksReactive(): Flow<List<DayTask>> =
        dayTaskDao.getTasksReactive().map(List<DayTaskCompound>::toModels)

    override suspend fun getTask(id: Long): DayTask =
        dayTaskDao.getTask(id).toModel()
}
