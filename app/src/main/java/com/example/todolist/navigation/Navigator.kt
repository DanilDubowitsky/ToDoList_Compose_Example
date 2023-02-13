package com.example.todolist.navigation

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class Navigator(
    val startScreen: Screen
) {

    private val _commands: MutableSharedFlow<RouterCommand> =
        MutableSharedFlow(extraBufferCapacity = 1)
    val commands: SharedFlow<RouterCommand> = _commands.asSharedFlow()

    fun performCommand(command: RouterCommand) {
        _commands.tryEmit(command)
    }

    sealed class Screen(val route: String) {
        object DayTasks : Screen("day_tasks")
        data class TaskOverview(
            val taskId: Long
        ) : Screen("task_overview/$taskId") {
            companion object {
                const val argumentId = "taskId"
                const val route = "task_overview/{$argumentId}"
            }
        }
        object TaskCreation : Screen("task_creation")
    }
}
