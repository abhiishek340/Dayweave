package com.lifesyncpro.app.domain.model

import java.util.Date

data class Activity(
    val id: String,
    val userId: String,
    val type: ActivityType,
    val startTime: Date,
    val endTime: Date? = null,
    val duration: Long? = null, // in minutes
    val calories: Int? = null,
    val steps: Int? = null,
    val distance: Float? = null, // in meters
    val heartRate: List<HeartRatePoint> = emptyList(),
    val location: List<LocationPoint> = emptyList(),
    val notes: String? = null
)

enum class ActivityType {
    WALKING,
    RUNNING,
    CYCLING,
    SWIMMING,
    WORKOUT,
    YOGA,
    MEDITATION,
    OTHER
}

data class HeartRatePoint(
    val timestamp: Date,
    val value: Int // beats per minute
)

data class LocationPoint(
    val timestamp: Date,
    val latitude: Double,
    val longitude: Double,
    val altitude: Double? = null,
    val accuracy: Float? = null,
    val speed: Float? = null
)

data class DailyActivitySummary(
    val date: Date,
    val userId: String,
    val totalSteps: Int = 0,
    val totalCalories: Int = 0,
    val totalDistance: Float = 0f, // in meters
    val activeMinutes: Int = 0,
    val activities: List<Activity> = emptyList(),
    val averageHeartRate: Int? = null,
    val peakHeartRate: Int? = null,
    val restingHeartRate: Int? = null
)

data class WeeklyActivitySummary(
    val weekStartDate: Date,
    val userId: String,
    val dailySummaries: List<DailyActivitySummary>,
    val totalSteps: Int = 0,
    val totalCalories: Int = 0,
    val totalDistance: Float = 0f,
    val totalActiveMinutes: Int = 0,
    val averageHeartRate: Int? = null,
    val goalProgress: GoalProgress
)

data class GoalProgress(
    val stepsProgress: Float, // percentage
    val workoutsProgress: Float,
    val activeMinutesProgress: Float,
    val caloriesProgress: Float
) 