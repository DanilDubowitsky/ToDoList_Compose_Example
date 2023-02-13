package com.example.todolist.domain.cases

import com.example.todolist.domain.repository.IDayTaskRepository

class GetTasksReactive(
    private val repository: IDayTaskRepository
) {
    suspend operator fun invoke() = repository.getTasksReactive()
}