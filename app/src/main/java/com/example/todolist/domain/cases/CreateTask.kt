package com.example.todolist.domain.cases

import com.example.todolist.domain.model.sql.DayTaskSQL
import com.example.todolist.domain.model.sql.TaskPointSQL
import com.example.todolist.domain.repository.IDayTaskRepository
import java.util.UUID

class CreateTask(
    private val repository: IDayTaskRepository
) {

    suspend operator fun invoke(
        taskTitle: String,
        taskPoints: List<TaskPointSQL>
    ) {
        val date = System.currentTimeMillis()
        val id = UUID.randomUUID().mostSignificantBits
        repository.addTask(DayTaskSQL(id, date, taskTitle, taskPoints))
    }
}