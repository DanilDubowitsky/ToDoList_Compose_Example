package com.example.todolist.ui.viewmodel.overview

import androidx.lifecycle.viewModelScope
import com.example.todolist.domain.cases.GetDayTask
import com.example.todolist.domain.cases.GetTaskPointsReactive
import com.example.todolist.domain.cases.UpdateTaskPointCompleteState
import com.example.todolist.domain.model.TaskPoint
import com.example.todolist.ui.converter.toUIModels
import com.example.todolist.ui.utils.TASK_DATE_TIME_FORMAT
import com.example.todolist.ui.utils.formatMillisToDisplayDate
import com.example.todolist.ui.viewmodel.base.BaseStateViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class TaskOverviewViewModel @Inject constructor(
    private val getTaskPointsReactive: GetTaskPointsReactive,
    private val updateTaskPointCompleteState: UpdateTaskPointCompleteState,
    private val getTask: GetDayTask
) : BaseStateViewModel<TaskOverviewState, TaskOverviewSideEffect>() {

    override fun createInitialState(): TaskOverviewState = TaskOverviewState()

    private var taskId: Long = 0

    fun loadData(
        taskId: Long
    ) {
        this.taskId = taskId
        viewModelScope.launch {
            val task = getTask(taskId)
            getTaskPointsReactive(taskId)
                .onEach { taskPoints ->
                    withContext(Dispatchers.Default) {
                        updateState {
                            copy(
                                taskTitle = task.title,
                                date = formatMillisToDisplayDate(task.date, TASK_DATE_TIME_FORMAT),
                                completeTaskPoints = taskPoints.filter(TaskPoint::completed)
                                    .toUIModels(),
                                incompleteTaskPoints = taskPoints.filter { !it.completed }
                                    .toUIModels()
                            )
                        }
                    }
                }.launchIn(this)
        }
    }

    fun updateTaskPointCompletion(id: Long, complete: Boolean) =
        viewModelScope.launch {
            updateTaskPointCompleteState(id, complete)
        }
}
