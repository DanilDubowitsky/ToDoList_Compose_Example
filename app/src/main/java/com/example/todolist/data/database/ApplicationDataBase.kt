package com.example.todolist.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todolist.data.dao.TaskPointDao
import com.example.todolist.data.dao.DayTaskDao
import com.example.todolist.data.database.ApplicationDataBase.Companion.DATA_BASE_VERSION
import com.example.todolist.data.entity.task.DayTaskEntity
import com.example.todolist.data.entity.point.TaskPointEntity

@Database(
    entities = [
        DayTaskEntity::class,
        TaskPointEntity::class
    ],
    version = DATA_BASE_VERSION
)
abstract class ApplicationDataBase : RoomDatabase() {

    abstract val dayTaskDao: DayTaskDao
    abstract val taskPointDao: TaskPointDao

    companion object {
        const val DATA_BASE_VERSION = 1
        const val DATA_BASE_NAME = "application_data_base"
    }
}
