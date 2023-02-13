package com.example.todolist.domain.cases

import com.example.todolist.domain.repository.IDayTaskRepository

class GetDayTask(
    private val dayTaskRepository: IDayTaskRepository
){
    suspend operator fun invoke(id: Long) =
        dayTaskRepository.getTask(id)
}
