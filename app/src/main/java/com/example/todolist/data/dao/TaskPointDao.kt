package com.example.todolist.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.todolist.data.entity.point.TaskPointEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskPointDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(point: TaskPointEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(points: List<TaskPointEntity>)

    @Query("""SELECT * FROM TaskPointEntity WHERE taskId = :taskId""")
    fun getPoints(taskId: Long): Flow<List<TaskPointEntity>>

    @Query("""UPDATE TaskPointEntity SET completed = :isComplete WHERE id = :id""")
    suspend fun updateCompleteState(isComplete: Boolean, id: Long)
}
