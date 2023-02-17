package com.example.todolist

import com.example.todolist.core.MockDayTaskRepository
import com.example.todolist.domain.cases.GetTasksReactive
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Test

class GetTasksTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun test_Get_Tasks() = runTest {
        val geTasksUseCase = GetTasksReactive(MockDayTaskRepository())
        val results = geTasksUseCase().toList()
        assertEquals(results.first().size, 2)
    }

}
