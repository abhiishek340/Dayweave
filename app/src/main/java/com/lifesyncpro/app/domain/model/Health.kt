package com.lifesyncpro.app.domain.model

import java.util.Date

data class HealthMetrics(
    val id: String,
    val userId: String,
    val timestamp: Date,
    val type: HealthMetricType,
    val value: Float,
    val unit: String,
    val notes: String? = null
)

enum class HealthMetricType {
    WEIGHT,
    BLOOD_PRESSURE,
    HEART_RATE,
    BLOOD_GLUCOSE,
    BODY_TEMPERATURE,
    OXYGEN_SATURATION,
    SLEEP_DURATION,
    WATER_INTAKE,
    BMI
}

data class SleepData(
    val id: String,
    val userId: String,
    val startTime: Date,
    val endTime: Date,
    val duration: Long, // in minutes
    val quality: SleepQuality,
    val stages: List<SleepStage>,
    val heartRate: List<HeartRatePoint> = emptyList(),
    val notes: String? = null
)

enum class SleepQuality {
    POOR,
    FAIR,
    GOOD,
    EXCELLENT
}

data class SleepStage(
    val type: SleepStageType,
    val startTime: Date,
    val endTime: Date,
    val duration: Long // in minutes
)

enum class SleepStageType {
    AWAKE,
    LIGHT,
    DEEP,
    REM
}

data class NutritionData(
    val id: String,
    val userId: String,
    val timestamp: Date,
    val mealType: MealType,
    val foods: List<FoodItem>,
    val totalCalories: Int,
    val totalProtein: Float, // in grams
    val totalCarbs: Float, // in grams
    val totalFat: Float, // in grams
    val notes: String? = null
)

enum class MealType {
    BREAKFAST,
    LUNCH,
    DINNER,
    SNACK
}

data class FoodItem(
    val name: String,
    val servingSize: Float,
    val servingUnit: String,
    val calories: Int,
    val protein: Float,
    val carbs: Float,
    val fat: Float,
    val nutrients: Map<String, Float> = emptyMap()
)

data class HealthSummary(
    val userId: String,
    val date: Date,
    val metrics: List<HealthMetrics>,
    val sleep: SleepData?,
    val nutrition: List<NutritionData>,
    val insights: List<HealthInsight>
)

data class HealthInsight(
    val type: InsightType,
    val title: String,
    val description: String,
    val severity: InsightSeverity,
    val recommendations: List<String>,
    val relatedMetrics: List<String>
)

enum class InsightType {
    SLEEP_PATTERN,
    ACTIVITY_LEVEL,
    NUTRITION,
    VITAL_SIGNS,
    GENERAL_WELLNESS
}

enum class InsightSeverity {
    INFO,
    SUGGESTION,
    WARNING,
    ALERT
} 