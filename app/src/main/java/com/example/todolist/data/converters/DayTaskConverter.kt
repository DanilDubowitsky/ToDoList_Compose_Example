package com.example.todolist.data.converters

import com.example.todolist.data.entity.point.TaskPointEntity
import com.example.todolist.data.entity.task.DayTaskEntity
import com.example.todolist.data.entity.task.compound.DayTaskCompound
import com.example.todolist.domain.model.DayTask
import com.example.todolist.domain.model.sql.DayTaskSQL

fun DayTaskSQL.toEntity() = DayTaskEntity(
    id,
    date,
    title
)

fun DayTaskCompound.toModel() = DayTask(
    dayTask.id,
    dayTask.title,
    dayTask.date,
    points.count(TaskPointEntity::completed),
    points.size,
    points.count { taskEntity -> !taskEntity.completed }
)

fun List<DayTaskCompound>.toModels() = this.map(DayTaskCompound::toModel)
