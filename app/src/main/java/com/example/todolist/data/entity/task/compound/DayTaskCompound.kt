package com.example.todolist.data.entity.task.compound

import androidx.room.Embedded
import androidx.room.Relation
import com.example.todolist.data.entity.point.TaskPointEntity
import com.example.todolist.data.entity.task.DayTaskEntity

data class DayTaskCompound(
    @Embedded
    val dayTask: DayTaskEntity,
    @Relation(parentColumn = "id", entityColumn = "taskId")
    val points: List<TaskPointEntity>
)
