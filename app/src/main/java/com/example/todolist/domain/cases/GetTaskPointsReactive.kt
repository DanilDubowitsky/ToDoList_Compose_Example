package com.example.todolist.domain.cases

import com.example.todolist.domain.repository.ITaskPointRepository

class GetTaskPointsReactive(
    private val taskPointRepository: ITaskPointRepository
) {

    suspend operator fun invoke(taskId: Long) =
        taskPointRepository.getTaskPoints(taskId)
}
