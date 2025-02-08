package com.lifesyncpro.app.domain.repository

import com.lifesyncpro.app.domain.model.*
import kotlinx.coroutines.flow.Flow
import java.util.Date

interface ActivityRepository {
    // Activity Tracking
    suspend fun startActivity(activity: Activity): Result<Activity>
    suspend fun stopActivity(id: String): Result<Activity>
    suspend fun pauseActivity(id: String): Result<Activity>
    suspend fun resumeActivity(id: String): Result<Activity>
    suspend fun getActivity(id: String): Result<Activity>
    suspend fun updateActivity(activity: Activity): Result<Activity>
    suspend fun deleteActivity(id: String): Result<Boolean>
    
    // Activity Observation
    fun observeCurrentActivity(userId: String): Flow<Activity?>
    fun observeActivities(userId: String): Flow<List<Activity>>
    fun observeActivityById(id: String): Flow<Activity>
    fun observeActivitiesByType(userId: String, type: ActivityType): Flow<List<Activity>>
    fun observeActivitiesByDateRange(
        userId: String,
        startDate: Date,
        endDate: Date
    ): Flow<List<Activity>>
    
    // Activity Summaries
    suspend fun getDailyActivitySummary(userId: String, date: Date): Result<DailyActivitySummary>
    suspend fun getWeeklyActivitySummary(userId: String, weekStartDate: Date): Result<WeeklyActivitySummary>
    fun observeDailyActivitySummary(userId: String): Flow<DailyActivitySummary>
    fun observeWeeklyActivitySummary(userId: String): Flow<WeeklyActivitySummary>
    
    // Heart Rate
    suspend fun addHeartRatePoint(activityId: String, heartRate: HeartRatePoint): Result<Activity>
    suspend fun addHeartRatePoints(activityId: String, heartRates: List<HeartRatePoint>): Result<Activity>
    suspend fun getHeartRateData(activityId: String): Result<List<HeartRatePoint>>
    
    // Location
    suspend fun addLocationPoint(activityId: String, location: LocationPoint): Result<Activity>
    suspend fun addLocationPoints(activityId: String, locations: List<LocationPoint>): Result<Activity>
    suspend fun getLocationData(activityId: String): Result<List<LocationPoint>>
    
    // Activity Goals
    suspend fun setActivityGoal(userId: String, type: ActivityType, target: Int): Result<Boolean>
    suspend fun getActivityGoal(userId: String, type: ActivityType): Result<Int>
    suspend fun getActivityGoalProgress(userId: String, type: ActivityType): Result<Float>
    
    // Activity Analytics
    suspend fun getActivityStats(
        userId: String,
        type: ActivityType,
        startDate: Date,
        endDate: Date
    ): Result<Map<String, Float>>
    
    suspend fun getActivityTrends(
        userId: String,
        type: ActivityType,
        startDate: Date,
        endDate: Date
    ): Result<List<DailyActivitySummary>>
    
    // Data Export/Import
    suspend fun exportActivityData(userId: String, startDate: Date, endDate: Date): Result<String>
    suspend fun importActivityData(userId: String, data: String): Result<Boolean>
} 