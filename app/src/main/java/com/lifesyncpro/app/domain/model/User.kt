package com.lifesyncpro.app.domain.model

data class User(
    val id: String,
    val email: String,
    val displayName: String,
    val photoUrl: String? = null,
    val healthProfile: HealthProfile? = null,
    val preferences: UserPreferences = UserPreferences()
)

data class HealthProfile(
    val weight: Float? = null, // in kg
    val height: Float? = null, // in cm
    val birthDate: String? = null,
    val gender: String? = null,
    val bloodType: String? = null,
    val medicalConditions: List<String> = emptyList(),
    val allergies: List<String> = emptyList()
)

data class UserPreferences(
    val isDarkMode: Boolean = false,
    val notificationsEnabled: Boolean = true,
    val reminderTime: String = "09:00", // 24-hour format
    val weeklyGoals: WeeklyGoals = WeeklyGoals(),
    val privacySettings: PrivacySettings = PrivacySettings()
)

data class WeeklyGoals(
    val steps: Int = 70000,
    val workouts: Int = 5,
    val sleepHours: Int = 49, // 7 hours per day
    val waterIntake: Int = 14000 // in ml
)

data class PrivacySettings(
    val shareHealthData: Boolean = false,
    val shareLocation: Boolean = false,
    val shareActivityData: Boolean = false,
    val allowAnalytics: Boolean = true
) 