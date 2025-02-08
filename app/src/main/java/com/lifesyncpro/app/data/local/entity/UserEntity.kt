package com.lifesyncpro.app.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.lifesyncpro.app.data.local.converter.DateConverter
import com.lifesyncpro.app.data.local.converter.ListConverter
import com.lifesyncpro.app.domain.model.HealthProfile
import com.lifesyncpro.app.domain.model.User
import com.lifesyncpro.app.domain.model.UserPreferences

@Entity(tableName = "users")
@TypeConverters(DateConverter::class, ListConverter::class)
data class UserEntity(
    @PrimaryKey
    val id: String,
    val email: String,
    val displayName: String,
    val photoUrl: String?,
    val weight: Float?,
    val height: Float?,
    val birthDate: String?,
    val gender: String?,
    val bloodType: String?,
    val medicalConditions: List<String>,
    val allergies: List<String>,
    val isDarkMode: Boolean,
    val notificationsEnabled: Boolean,
    val reminderTime: String,
    val weeklyStepsGoal: Int,
    val weeklyWorkoutsGoal: Int,
    val weeklySleepHoursGoal: Int,
    val weeklyWaterIntakeGoal: Int,
    val shareHealthData: Boolean,
    val shareLocation: Boolean,
    val shareActivityData: Boolean,
    val allowAnalytics: Boolean
) {
    fun toUser(): User {
        val healthProfile = HealthProfile(
            weight = weight,
            height = height,
            birthDate = birthDate,
            gender = gender,
            bloodType = bloodType,
            medicalConditions = medicalConditions,
            allergies = allergies
        )

        val preferences = UserPreferences(
            isDarkMode = isDarkMode,
            notificationsEnabled = notificationsEnabled,
            reminderTime = reminderTime,
            weeklyGoals = com.lifesyncpro.app.domain.model.WeeklyGoals(
                steps = weeklyStepsGoal,
                workouts = weeklyWorkoutsGoal,
                sleepHours = weeklySleepHoursGoal,
                waterIntake = weeklyWaterIntakeGoal
            ),
            privacySettings = com.lifesyncpro.app.domain.model.PrivacySettings(
                shareHealthData = shareHealthData,
                shareLocation = shareLocation,
                shareActivityData = shareActivityData,
                allowAnalytics = allowAnalytics
            )
        )

        return User(
            id = id,
            email = email,
            displayName = displayName,
            photoUrl = photoUrl,
            healthProfile = healthProfile,
            preferences = preferences
        )
    }

    companion object {
        fun fromUser(user: User): UserEntity {
            return UserEntity(
                id = user.id,
                email = user.email,
                displayName = user.displayName,
                photoUrl = user.photoUrl,
                weight = user.healthProfile?.weight,
                height = user.healthProfile?.height,
                birthDate = user.healthProfile?.birthDate,
                gender = user.healthProfile?.gender,
                bloodType = user.healthProfile?.bloodType,
                medicalConditions = user.healthProfile?.medicalConditions ?: emptyList(),
                allergies = user.healthProfile?.allergies ?: emptyList(),
                isDarkMode = user.preferences.isDarkMode,
                notificationsEnabled = user.preferences.notificationsEnabled,
                reminderTime = user.preferences.reminderTime,
                weeklyStepsGoal = user.preferences.weeklyGoals.steps,
                weeklyWorkoutsGoal = user.preferences.weeklyGoals.workouts,
                weeklySleepHoursGoal = user.preferences.weeklyGoals.sleepHours,
                weeklyWaterIntakeGoal = user.preferences.weeklyGoals.waterIntake,
                shareHealthData = user.preferences.privacySettings.shareHealthData,
                shareLocation = user.preferences.privacySettings.shareLocation,
                shareActivityData = user.preferences.privacySettings.shareActivityData,
                allowAnalytics = user.preferences.privacySettings.allowAnalytics
            )
        }
    }
} 