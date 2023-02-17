package com.example.todolist

import com.example.todolist.core.MockDayTaskRepository
import com.example.todolist.domain.cases.GetDayTask
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

class GetTaskTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun test_Get_Task() = runTest {
        val getTaskUseCase = GetDayTask(MockDayTaskRepository())
        val task = getTaskUseCase(1)
        assertEquals(task.id, 1)
    }
}
