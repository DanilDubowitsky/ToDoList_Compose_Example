package com.example.todolist.ui.components.tasks

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.todolist.R
import com.example.todolist.ui.components.common.TitleText
import com.example.todolist.ui.model.task.DayTaskUI
import com.example.todolist.ui.theme.fabColor
import com.example.todolist.ui.theme.textPrimaryLightTheme
import com.example.todolist.ui.theme.textSecondaryLightTheme
import com.example.todolist.ui.viewmodel.tasks.DayTasksViewModel

@Composable
fun DayTaskComponent(item: DayTaskUI, onItemClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable(interactionSource = MutableInteractionSource(),
                indication = rememberRipple(bounded = true),
                onClick = {
                    onItemClick()
                }
            )
    ) {
        Text(
            text = item.title,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp),
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = item.date,
            fontSize = 15.sp,
            color = textPrimaryLightTheme,
            fontWeight = FontWeight.Medium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp)
        )
        Text(
            text = stringResource(
                com.example.todolist.R.string.todo_days_tasks_template,
                item.completedTasks.toString(),
                item.totalTasks.toString()
            ),
            color = textSecondaryLightTheme,
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp)
        )
    }
}

@Composable
fun DayTasksComponent(
    viewModel: DayTasksViewModel = hiltViewModel(),
) {
    val dayTasks by viewModel.state.collectAsState()

    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (fab, noTasksText, title, tasksColumn) = createRefs()
        TitleText(
            text = stringResource(id = R.string.tasks_title),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                .constrainAs(title) {
                    top.linkTo(parent.top)
                }
        )
        when {
            dayTasks.isLoading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            dayTasks.dayTasks.isEmpty() -> {
                Text(
                    text = stringResource(id = R.string.tasks_empty_label),
                    modifier = Modifier
                        .fillMaxWidth()
                        .constrainAs(noTasksText) {
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                        },
                    textAlign = TextAlign.Center
                )
            }
            else -> {
                LazyColumn(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .constrainAs(tasksColumn) {
                            top.linkTo(title.bottom)
                        }
                ) {
                    itemsIndexed(dayTasks.dayTasks) { index, todoDay ->
                        DayTaskComponent(item = todoDay) {
                            viewModel.navigateToOverview(todoDay.id)
                        }
                        if (index < dayTasks.dayTasks.lastIndex)
                            Divider()
                    }
                }
            }
        }

        FloatingActionButton(
            onClick = {
                viewModel.navigateToTaskCreation()
            },
            contentColor = fabColor,
            backgroundColor = fabColor,
            modifier = Modifier.constrainAs(fab) {
                bottom.linkTo(parent.bottom, 16.dp)
                end.linkTo(parent.end, 16.dp)
            }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_add),
                tint = Color.White,
                contentDescription = ""
            )
        }
    }
}
