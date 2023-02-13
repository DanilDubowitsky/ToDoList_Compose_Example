package com.example.todolist.ui.viewmodel.tasks

import androidx.lifecycle.viewModelScope
import com.example.todolist.domain.cases.GetTasksReactive
import com.example.todolist.domain.model.DayTask
import com.example.todolist.navigation.Navigator
import com.example.todolist.navigation.RouterCommand
import com.example.todolist.ui.converter.toUIModels
import com.example.todolist.ui.viewmodel.base.BaseStateViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DayTasksViewModel @Inject constructor(
    private val navigator: Navigator,
    private val getDayTasksReactive: GetTasksReactive
) : BaseStateViewModel<DayTasksState, Any>() {

    private var dayTasks: List<DayTask> = emptyList()

    override fun createInitialState(): DayTasksState = DayTasksState(emptyList())

    init {
        loadTaskData()
    }

    private fun loadTaskData() = viewModelScope.launch {
        updateState {
            copy(isLoading = true)
        }
        getDayTasksReactive().onEach { tasks ->
            dayTasks = tasks
            handleDayTasks(tasks)
        }.launchIn(this)
    }

    private suspend fun handleDayTasks(tasks: List<DayTask>) {
        withContext(Dispatchers.Default) {
            updateState {
                copy(dayTasks = tasks.toUIModels(), isLoading = false)
            }
        }
    }

    fun navigateToTaskCreation() {
        navigator.performCommand(
            RouterCommand.NavigateTo(
                Navigator.Screen.TaskCreation
            )
        )
    }

    fun navigateToOverview(id: Long) {
        navigator.performCommand(
            RouterCommand.NavigateTo(
                Navigator.Screen.TaskOverview(id)
            )
        )
    }
}
