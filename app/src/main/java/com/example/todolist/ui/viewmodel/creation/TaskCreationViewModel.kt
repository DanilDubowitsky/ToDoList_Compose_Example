package com.example.todolist.ui.viewmodel.creation

import androidx.lifecycle.viewModelScope
import com.example.todolist.domain.cases.CreateTask
import com.example.todolist.domain.model.sql.TaskPointSQL
import com.example.todolist.navigation.Navigator
import com.example.todolist.navigation.RouterCommand
import com.example.todolist.ui.model.point.TaskPointUI
import com.example.todolist.ui.viewmodel.base.BaseStateViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskCreationViewModel @Inject constructor(
    private val createNewTask: CreateTask,
    private val navigator: Navigator
) : BaseStateViewModel<TaskCreationState, Any>() {

    override fun createInitialState(): TaskCreationState = TaskCreationState()

    fun addNewTaskPoint(
        taskBody: String
    ) {
        updateState {
            copy(
                taskPoints = taskPoints.plus(
                    TaskPointUI(
                        body = taskBody,
                        completed = false
                    )
                )
            )
        }
    }

    fun deleteTaskPoint(index: Int) {
        val taskPoints = state.value.taskPoints.toMutableList()
        taskPoints.removeAt(index)
        updateState {
            copy(taskPoints = taskPoints)
        }
    }

    fun saveNewTask(title: String) =
        viewModelScope.launch {
            val taskPointsSQL = state.value.taskPoints.map { point ->
                TaskPointSQL(point.body, point.completed)
            }
            createNewTask(title, taskPointsSQL)
            navigator.performCommand(RouterCommand.Exit)
        }

    fun changeTaskPoint(index: Int, newBody: String) {
        val taskPoints = state.value.taskPoints.toMutableList()
        val taskPoint = taskPoints[index].copy(body = newBody)
        taskPoints[index] = taskPoint
        updateState {
            copy(taskPoints = taskPoints)
        }
    }
}
