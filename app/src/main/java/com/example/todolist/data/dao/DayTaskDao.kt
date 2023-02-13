package com.example.todolist.data.dao

import androidx.room.*
import com.example.todolist.data.converters.toEntities
import com.example.todolist.data.converters.toEntity
import com.example.todolist.data.entity.point.TaskPointEntity
import com.example.todolist.data.entity.task.DayTaskEntity
import com.example.todolist.data.entity.task.compound.DayTaskCompound
import com.example.todolist.domain.model.sql.DayTaskSQL
import kotlinx.coroutines.flow.Flow

@Dao
interface DayTaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(pointDay: DayTaskEntity)

    @Transaction
    suspend fun insertOrUpdate(task: DayTaskSQL) {
        val taskEntity = task.toEntity()
        val taskPoints = task.points.toEntities(task.id)
        insertOrUpdate(taskEntity, taskPoints)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(
        pointDay: DayTaskEntity,
        taskPoint: List<TaskPointEntity>
    )

    @Transaction
    @Query("""SELECT * FROM DayTaskEntity""")
    fun getTasksReactive(): Flow<List<DayTaskCompound>>

    @Transaction
    @Query("""SELECT * FROM DayTaskEntity WHERE id = :id""")
    suspend fun getTask(id: Long): DayTaskCompound
}