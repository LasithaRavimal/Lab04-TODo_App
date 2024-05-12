package com.coding.meet.todo_app.dao

import androidx.room.*
import com.coding.meet.todo_app.models.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    // Retrieve tasks sorted by task title, ascending or descending
    @Query("""SELECT * FROM Task ORDER BY
        CASE WHEN :isAsc = 1 THEN taskTitle END ASC, 
        CASE WHEN :isAsc = 0 THEN taskTitle END DESC""")
    fun getTaskListSortByTaskTitle(isAsc: Boolean): Flow<List<Task>>

    // Retrieve tasks sorted by task date, ascending or descending
    @Query("""SELECT * FROM Task ORDER BY
        CASE WHEN :isAsc = 1 THEN date END ASC, 
        CASE WHEN :isAsc = 0 THEN date END DESC""")
    fun getTaskListSortByTaskDate(isAsc: Boolean): Flow<List<Task>>

    // Insert a task into the database
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: Task): Long

    // Delete a task from the database (first way)
    @Delete
    suspend fun deleteTask(task: Task): Int

    // Delete a task from the database using its ID (second way)
    @Query("DELETE FROM Task WHERE taskId == :taskId")
    suspend fun deleteTaskUsingId(taskId: String): Int

    // Update a task in the database
    @Update
    suspend fun updateTask(task: Task): Int

    // Update specific fields of a task in the database
    @Query("UPDATE Task SET taskTitle=:title, description = :description WHERE taskId = :taskId")
    suspend fun updateTaskPaticularField(taskId: String, title: String, description: String): Int

    // Search tasks based on a query string
    @Query("SELECT * FROM Task WHERE taskTitle LIKE :query ORDER BY date DESC")
    fun searchTaskList(query: String): Flow<List<Task>>
}
