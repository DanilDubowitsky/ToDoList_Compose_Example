package com.example.todolist

import com.example.todolist.core.MockTaskPointsRepository
import com.example.todolist.domain.cases.GetTaskPointsReactive
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Test

class GetTaskPointsTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `Test task points for right task id`() = runTest {
        val getTaskPoints = GetTaskPointsReactive(MockTaskPointsRepository())
        val taskPointsList = getTaskPoints(1L).toList().first()
        val isEqualToId = taskPointsList.all { point ->
            point.taskId == 1L
        }
        assertEquals(isEqualToId, true)
    }
}
