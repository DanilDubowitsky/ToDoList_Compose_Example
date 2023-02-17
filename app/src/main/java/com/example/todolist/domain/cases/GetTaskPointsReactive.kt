package com.example.todolist.domain.cases

import com.example.todolist.domain.repository.ITaskPointRepository
import javax.inject.Inject

class GetTaskPointsReactive @Inject constructor(
    private val taskPointRepository: ITaskPointRepository
) {

    suspend operator fun invoke(taskId: Long) =
        taskPointRepository.getTaskPoints(taskId)
}
