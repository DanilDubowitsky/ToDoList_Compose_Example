package com.example.todolist.ui.components.overview

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Checkbox
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.todolist.R
import com.example.todolist.domain.model.TaskPoint
import com.example.todolist.ui.model.point.TaskPointUI
import com.example.todolist.ui.theme.disableColor
import com.example.todolist.ui.theme.dividerColor
import com.example.todolist.ui.theme.textSecondaryLightTheme
import com.example.todolist.ui.viewmodel.overview.TaskOverviewViewModel

private const val LOAD_TASK_DATA_EFFECT_KEY = "load_task_data_effect_key"

@Composable
fun TaskOverviewComponent(
    viewModel: TaskOverviewViewModel = hiltViewModel(),
    taskId: Long
) {
    val state = viewModel.state.collectAsState()
    LaunchedEffect(key1 = LOAD_TASK_DATA_EFFECT_KEY) {
        viewModel.loadData(taskId)
    }
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = state.value.date,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp),
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = state.value.taskTitle,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )
        Text(
            text = stringResource(
                id = R.string.complete_and_incomplete_task_points_template,
                state.value.completeTaskPoints.size,
                state.value.incompleteTaskPoints.size
            ),
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp,
            color = textSecondaryLightTheme,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        )
        Divider(
            color = dividerColor,
            thickness = 1.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        )
        if (state.value.incompleteTaskPoints.isNotEmpty()) {
            Text(
                text = stringResource(id = R.string.incomplete),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                color = textSecondaryLightTheme,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            items(state.value.incompleteTaskPoints) { item ->
                TaskPointComponent(taskPoint = item) { checked ->
                    viewModel.updateTaskPointCompletion(item.id, checked)
                }
            }
        }
        if (state.value.completeTaskPoints.isNotEmpty()) {
            Text(
                text = stringResource(id = R.string.completed),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                color = textSecondaryLightTheme,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            items(state.value.completeTaskPoints) { item ->
                TaskPointComponent(taskPoint = item) { checked ->
                    viewModel.updateTaskPointCompletion(item.id, checked)
                }
            }
        }
    }
}

@Composable
fun TaskPointComponent(taskPoint: TaskPointUI, onCheckBoxClick: (checked: Boolean) -> Unit) {
    Row(modifier = Modifier.fillMaxWidth()
        .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically) {
        Checkbox(checked = taskPoint.completed, onCheckedChange = { checked ->
            onCheckBoxClick(checked)
        })
        val textColor = if (taskPoint.completed) disableColor
        else textSecondaryLightTheme
        Text(
            text = taskPoint.body,
            modifier = Modifier.padding(horizontal = 16.dp),
            color = textColor,
            fontWeight = FontWeight.Medium,
            fontSize = 18.sp
        )
    }
}

@Preview
@Composable
fun TaskOverviewPreview() {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "13 марта, 2018",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp),
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Sample title",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )
    }
}
