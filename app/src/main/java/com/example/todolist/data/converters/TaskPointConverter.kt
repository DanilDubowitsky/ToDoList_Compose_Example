package com.example.todolist.data.converters

import com.example.todolist.data.entity.point.TaskPointEntity
import com.example.todolist.domain.model.TaskPoint
import com.example.todolist.domain.model.sql.TaskPointSQL

fun TaskPointSQL.toEntity(dayTaskId: Long) = TaskPointEntity(
    taskId = dayTaskId,
    completed = completed,
    body = body
)

fun List<TaskPointSQL>.toEntities(dayTaskId: Long) = this.map { point ->
    point.toEntity(dayTaskId)
}

fun TaskPointEntity.toModel() = TaskPoint(
    id,
    taskId,
    body,
    completed
)

fun List<TaskPointEntity>.toModels() = this.map(TaskPointEntity::toModel)
