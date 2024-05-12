package com.coding.meet.todo_app.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.room.Query
import com.coding.meet.todo_app.models.Task
import com.coding.meet.todo_app.repository.TaskRepository
import com.coding.meet.todo_app.utils.Resource

class TaskViewModel(application: Application) : AndroidViewModel(application) {

    // Initialize the TaskRepository instance
    private val taskRepository = TaskRepository(application)

    // LiveData to observe the task state flow
    val taskStateFlow get() =  taskRepository.taskStateFlow

    // LiveData to observe the status updates
    val statusLiveData get() =  taskRepository.statusLiveData

    // LiveData to observe the sorting preferences
    val sortByLiveData get() =  taskRepository.sortByLiveData

    // Method to set the sorting preferences
    fun setSortBy(sort:Pair<String,Boolean>){
        taskRepository.setSortBy(sort)
    }

    // Method to get the list of tasks
    fun getTaskList(isAsc : Boolean, sortByName:String) {
        taskRepository.getTaskList(isAsc, sortByName)
    }

    // Method to insert a new task
    fun insertTask(task: Task){
        taskRepository.insertTask(task)
    }

    // Method to delete a task
    fun deleteTask(task: Task) {
        taskRepository.deleteTask(task)
    }

    // Method to delete a task using its ID
    fun deleteTaskUsingId(taskId: String){
        taskRepository.deleteTaskUsingId(taskId)
    }

    // Method to update a task
    fun updateTask(task: Task) {
        taskRepository.updateTask(task)
    }

    // Method to update a particular field of a task
    fun updateTaskPaticularField(taskId: String,title:String,description:String) {
        taskRepository.updateTaskPaticularField(taskId, title, description)
    }

    // Method to search for tasks based on a query
    fun searchTaskList(query: String){
        taskRepository.searchTaskList(query)
    }
}
