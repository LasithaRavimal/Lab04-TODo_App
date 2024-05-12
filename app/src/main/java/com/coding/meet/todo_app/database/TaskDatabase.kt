package com.coding.meet.todo_app.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.coding.meet.todo_app.converters.TypeConverter
import com.coding.meet.todo_app.dao.TaskDao
import com.coding.meet.todo_app.models.Task

@Database(
    entities = [Task::class], // Define entities (tables) in the database
    version = 1, // Define database version
    exportSchema = false // Set to false to avoid exporting schema
)
@TypeConverters(TypeConverter::class) // Specify the TypeConverter for Date conversion
abstract class TaskDatabase : RoomDatabase() {

    // Define an abstract method to access the TaskDao
    abstract val taskDao: TaskDao

    // Companion object to provide methods for getting database instance
    companion object {
        @Volatile
        private var INSTANCE: TaskDatabase? = null

        // Get a database instance
        fun getInstance(context: Context): TaskDatabase {
            synchronized(this) {
                // Return the existing instance if it exists, otherwise create a new one
                return INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    TaskDatabase::class.java,
                    "task_db" // Database name
                ).build().also {
                    INSTANCE = it
                }
            }
        }
    }
}
