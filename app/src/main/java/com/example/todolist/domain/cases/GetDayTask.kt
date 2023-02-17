package com.example.todolist.domain.cases

import com.example.todolist.domain.repository.IDayTaskRepository
import javax.inject.Inject

class GetDayTask @Inject constructor(
    private val dayTaskRepository: IDayTaskRepository
){
    suspend operator fun invoke(id: Long) =
        dayTaskRepository.getTask(id)
}
