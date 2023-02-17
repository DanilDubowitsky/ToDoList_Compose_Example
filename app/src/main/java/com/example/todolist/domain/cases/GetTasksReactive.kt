package com.example.todolist.domain.cases

import com.example.todolist.domain.repository.IDayTaskRepository
import javax.inject.Inject

class GetTasksReactive @Inject constructor(
    private val repository: IDayTaskRepository
) {
    suspend operator fun invoke() = repository.getTasksReactive()
}