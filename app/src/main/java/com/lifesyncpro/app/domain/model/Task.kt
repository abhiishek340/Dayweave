package com.lifesyncpro.app.domain.model

import java.util.Date

data class Task(
    val id: String,
    val userId: String,
    val title: String,
    val description: String? = null,
    val dueDate: Date? = null,
    val priority: TaskPriority = TaskPriority.MEDIUM,
    val status: TaskStatus = TaskStatus.PENDING,
    val category: TaskCategory? = null,
    val tags: List<String> = emptyList(),
    val reminder: TaskReminder? = null,
    val subtasks: List<Subtask> = emptyList(),
    val attachments: List<TaskAttachment> = emptyList(),
    val createdAt: Date,
    val updatedAt: Date,
    val completedAt: Date? = null,
    val recurrence: TaskRecurrence? = null
)

enum class TaskPriority {
    LOW,
    MEDIUM,
    HIGH,
    URGENT
}

enum class TaskStatus {
    PENDING,
    IN_PROGRESS,
    COMPLETED,
    ARCHIVED,
    DELETED
}

data class TaskCategory(
    val id: String,
    val name: String,
    val color: String,
    val icon: String? = null
)

data class TaskReminder(
    val time: Date,
    val type: ReminderType,
    val notificationId: String? = null
)

enum class ReminderType {
    ONE_TIME,
    RECURRING
}

data class Subtask(
    val id: String,
    val title: String,
    val isCompleted: Boolean = false,
    val completedAt: Date? = null
)

data class TaskAttachment(
    val id: String,
    val name: String,
    val type: AttachmentType,
    val url: String,
    val size: Long, // in bytes
    val uploadedAt: Date
)

enum class AttachmentType {
    IMAGE,
    DOCUMENT,
    AUDIO,
    VIDEO,
    OTHER
}

data class TaskRecurrence(
    val frequency: RecurrenceFrequency,
    val interval: Int = 1,
    val endDate: Date? = null,
    val endAfterOccurrences: Int? = null
)

enum class RecurrenceFrequency {
    DAILY,
    WEEKLY,
    MONTHLY,
    YEARLY
}

data class TaskList(
    val id: String,
    val userId: String,
    val name: String,
    val description: String? = null,
    val color: String? = null,
    val icon: String? = null,
    val tasks: List<Task> = emptyList(),
    val isDefault: Boolean = false,
    val createdAt: Date,
    val updatedAt: Date
)

data class TaskStatistics(
    val userId: String,
    val totalTasks: Int,
    val completedTasks: Int,
    val pendingTasks: Int,
    val overdueTasks: Int,
    val completionRate: Float, // percentage
    val averageCompletionTime: Long? = null, // in minutes
    val tasksByPriority: Map<TaskPriority, Int>,
    val tasksByCategory: Map<String, Int>,
    val tasksByStatus: Map<TaskStatus, Int>
) 