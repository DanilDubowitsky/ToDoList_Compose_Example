package com.example.todolist.ui.components.creation

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.todolist.R
import com.example.todolist.ui.components.common.DefaultTextField
import com.example.todolist.ui.components.common.TitleText
import com.example.todolist.ui.theme.borderColorLight
import com.example.todolist.ui.theme.textPrimaryLightTheme
import com.example.todolist.ui.theme.textSecondaryLightTheme
import com.example.todolist.ui.viewmodel.creation.TaskCreationViewModel

@Composable
fun DayTaskCreationComponent(
    viewModel: TaskCreationViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsState()
    var taskTitleText by rememberSaveable {
        mutableStateOf("")
    }
    Column(modifier = Modifier.fillMaxSize()) {
        ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
            val (title, saveBtn) = createRefs()
            TitleText(
                text = stringResource(id = R.string.new_task_title_label),
                modifier = Modifier
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                    .constrainAs(title) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )
            if (taskTitleText.isNotEmpty() && state.value.taskPoints.isNotEmpty()) {
                IconButton(onClick = {
                    viewModel.saveNewTask(taskTitleText)
                }, modifier = Modifier.constrainAs(saveBtn) {
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_check_mark),
                        contentDescription = "",
                        tint = textPrimaryLightTheme
                    )
                }
            }
        }
        DefaultTextField(
            taskTitleText,
            onValueChange = { value ->
                taskTitleText = value
            }, modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .border(
                    2.dp,
                    color = borderColorLight,
                    shape = RoundedCornerShape(8.dp)
                ),
            placeHolder = {
                Text(
                    text = stringResource(id = R.string.task_title_hint),
                    color = textSecondaryLightTheme,
                    fontSize = 14.sp
                )
            }
        )
        AddTaskComponent(viewModel)
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            itemsIndexed(state.value.taskPoints) { index, item ->
                TaskPointTemp(
                    taskText = item.body,
                    viewModel = viewModel,
                    index = index
                )
            }
        }
    }
}

@Composable
fun AddTaskComponent(
    viewModel: TaskCreationViewModel
) {
    Row {
        var textValue by rememberSaveable {
            mutableStateOf("")
        }
        DefaultTextField(textValue, { value ->
            textValue = value
        }, modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .border(
                2.dp, color = borderColorLight,
                shape = RoundedCornerShape(8.dp)
            ),
            placeHolder = {
                Text(
                    text = stringResource(id = R.string.task_point_hint),
                    color = textSecondaryLightTheme,
                    fontSize = 14.sp
                )
            },
            trailingIcon = {
                if (textValue.isNotEmpty()) {
                    IconButton(onClick = {
                        viewModel.addNewTaskPoint(textValue)
                        textValue = ""
                    }) {
                        Icon(
                            painter = painterResource(
                                id = R.drawable.ic_check_mark
                            ),
                            contentDescription = "",
                            tint = textPrimaryLightTheme
                        )
                    }
                }
            }
        )
    }
}

@Composable
fun TaskPointTemp(
    index: Int,
    taskText: String,
    viewModel: TaskCreationViewModel
) {
    ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
        val (body, deleteBtn) = createRefs()
        Text(
            text = taskText,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .constrainAs(body) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(deleteBtn.start)
                    width = Dimension.fillToConstraints
                },
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            color = textPrimaryLightTheme
        )
        IconButton(onClick = {
            viewModel.deleteTaskPoint(index)
        }, modifier = Modifier.constrainAs(deleteBtn) {
            top.linkTo(body.top)
            bottom.linkTo(body.bottom)
            end.linkTo(parent.end)
        }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_delete),
                contentDescription = "",
                tint = Color.Red
            )
        }
    }
}
