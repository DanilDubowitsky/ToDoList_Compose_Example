package com.example.todolist.domain.cases

import com.example.todolist.domain.repository.ITaskPointRepository

class UpdateTaskPointCompleteState(
    private val taskPointRepository: ITaskPointRepository
) {

    suspend operator fun invoke(
        taskId: Long,
        isComplete: Boolean
    ) = taskPointRepository.updateCompleteState(isComplete, taskId)
}
