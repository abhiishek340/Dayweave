package com.lifesyncpro.app.domain.repository

import com.lifesyncpro.app.domain.model.*
import kotlinx.coroutines.flow.Flow
import java.util.Date

interface TaskRepository {
    suspend fun createTask(task: Task): Result<Task>
    suspend fun getTaskById(id: String): Result<Task>
    suspend fun updateTask(task: Task): Result<Task>
    suspend fun deleteTask(id: String): Result<Boolean>
    suspend fun completeTask(id: String): Result<Task>
    suspend fun uncompleteTask(id: String): Result<Task>
    suspend fun addSubtask(taskId: String, subtask: Subtask): Result<Task>
    suspend fun updateSubtask(taskId: String, subtask: Subtask): Result<Task>
    suspend fun deleteSubtask(taskId: String, subtaskId: String): Result<Task>
    suspend fun addAttachment(taskId: String, attachment: TaskAttachment): Result<Task>
    suspend fun deleteAttachment(taskId: String, attachmentId: String): Result<Task>
    suspend fun createTaskList(taskList: TaskList): Result<TaskList>
    suspend fun getTaskList(id: String): Result<TaskList>
    suspend fun updateTaskList(taskList: TaskList): Result<TaskList>
    suspend fun deleteTaskList(id: String): Result<Boolean>
    suspend fun getTaskStatistics(userId: String): Result<TaskStatistics>
    
    fun observeAllTasks(userId: String): Flow<List<Task>>
    fun observeTaskById(id: String): Flow<Task>
    fun observeTasksByList(listId: String): Flow<List<Task>>
    fun observeTasksByStatus(userId: String, status: TaskStatus): Flow<List<Task>>
    fun observeTasksByPriority(userId: String, priority: TaskPriority): Flow<List<Task>>
    fun observeTasksByDueDate(userId: String, startDate: Date, endDate: Date): Flow<List<Task>>
    fun observeOverdueTasks(userId: String): Flow<List<Task>>
    fun observeTaskLists(userId: String): Flow<List<TaskList>>
    fun observeTaskStatistics(userId: String): Flow<TaskStatistics>
    
    suspend fun searchTasks(
        userId: String,
        query: String,
        filters: TaskSearchFilters = TaskSearchFilters()
    ): Result<List<Task>>
}

data class TaskSearchFilters(
    val status: List<TaskStatus> = emptyList(),
    val priority: List<TaskPriority> = emptyList(),
    val categories: List<String> = emptyList(),
    val startDate: Date? = null,
    val endDate: Date? = null,
    val tags: List<String> = emptyList(),
    val listIds: List<String> = emptyList()
) 