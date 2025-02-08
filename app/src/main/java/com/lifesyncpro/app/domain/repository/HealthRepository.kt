package com.lifesyncpro.app.domain.repository

import com.lifesyncpro.app.domain.model.*
import kotlinx.coroutines.flow.Flow
import java.util.Date

interface HealthRepository {
    // Health Metrics
    suspend fun addHealthMetric(metric: HealthMetrics): Result<HealthMetrics>
    suspend fun getHealthMetric(id: String): Result<HealthMetrics>
    suspend fun updateHealthMetric(metric: HealthMetrics): Result<HealthMetrics>
    suspend fun deleteHealthMetric(id: String): Result<Boolean>
    fun observeHealthMetrics(userId: String, type: HealthMetricType): Flow<List<HealthMetrics>>
    
    // Sleep Data
    suspend fun addSleepData(sleepData: SleepData): Result<SleepData>
    suspend fun getSleepData(id: String): Result<SleepData>
    suspend fun updateSleepData(sleepData: SleepData): Result<SleepData>
    suspend fun deleteSleepData(id: String): Result<Boolean>
    fun observeSleepData(userId: String): Flow<List<SleepData>>
    
    // Nutrition
    suspend fun addNutritionData(nutritionData: NutritionData): Result<NutritionData>
    suspend fun getNutritionData(id: String): Result<NutritionData>
    suspend fun updateNutritionData(nutritionData: NutritionData): Result<NutritionData>
    suspend fun deleteNutritionData(id: String): Result<Boolean>
    fun observeNutritionData(userId: String): Flow<List<NutritionData>>
    
    // Health Summary
    suspend fun getHealthSummary(userId: String, date: Date): Result<HealthSummary>
    fun observeHealthSummary(userId: String): Flow<HealthSummary>
    
    // Health Insights
    suspend fun generateHealthInsights(userId: String): Result<List<HealthInsight>>
    fun observeHealthInsights(userId: String): Flow<List<HealthInsight>>
    
    // Google Fit Integration
    suspend fun syncWithGoogleFit(userId: String): Result<Boolean>
    suspend fun disconnectGoogleFit(userId: String): Result<Boolean>
    suspend fun isGoogleFitConnected(userId: String): Result<Boolean>
    
    // Data Export/Import
    suspend fun exportHealthData(userId: String, startDate: Date, endDate: Date): Result<String>
    suspend fun importHealthData(userId: String, data: String): Result<Boolean>
    
    // Health Goals
    suspend fun setHealthGoal(userId: String, type: HealthMetricType, target: Float): Result<Boolean>
    suspend fun getHealthGoal(userId: String, type: HealthMetricType): Result<Float>
    suspend fun getHealthGoalProgress(userId: String, type: HealthMetricType): Result<Float>
    
    // Health Analytics
    suspend fun getHealthTrends(
        userId: String,
        type: HealthMetricType,
        startDate: Date,
        endDate: Date
    ): Result<List<HealthMetrics>>
    
    suspend fun getAggregatedStats(
        userId: String,
        type: HealthMetricType,
        startDate: Date,
        endDate: Date
    ): Result<Map<String, Float>>
} 