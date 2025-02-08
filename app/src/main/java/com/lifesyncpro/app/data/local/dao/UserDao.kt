package com.lifesyncpro.app.data.local.dao

import androidx.room.*
import com.lifesyncpro.app.data.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity)

    @Update
    suspend fun updateUser(user: UserEntity)

    @Delete
    suspend fun deleteUser(user: UserEntity)

    @Query("SELECT * FROM users WHERE id = :userId")
    suspend fun getUserById(userId: String): UserEntity?

    @Query("SELECT * FROM users WHERE id = :userId")
    fun observeUserById(userId: String): Flow<UserEntity?>

    @Query("SELECT * FROM users")
    fun observeAllUsers(): Flow<List<UserEntity>>

    @Query("DELETE FROM users")
    suspend fun deleteAllUsers()

    @Transaction
    suspend fun upsertUser(user: UserEntity) {
        val existingUser = getUserById(user.id)
        if (existingUser == null) {
            insertUser(user)
        } else {
            updateUser(user)
        }
    }

    @Query("SELECT * FROM users WHERE email = :email LIMIT 1")
    suspend fun getUserByEmail(email: String): UserEntity?

    @Query("UPDATE users SET photoUrl = :photoUrl WHERE id = :userId")
    suspend fun updateUserPhoto(userId: String, photoUrl: String)

    @Query("UPDATE users SET displayName = :displayName WHERE id = :userId")
    suspend fun updateDisplayName(userId: String, displayName: String)

    @Query("""
        UPDATE users SET 
        weight = :weight,
        height = :height,
        birthDate = :birthDate,
        gender = :gender,
        bloodType = :bloodType,
        medicalConditions = :medicalConditions,
        allergies = :allergies
        WHERE id = :userId
    """)
    suspend fun updateHealthProfile(
        userId: String,
        weight: Float?,
        height: Float?,
        birthDate: String?,
        gender: String?,
        bloodType: String?,
        medicalConditions: List<String>,
        allergies: List<String>
    )

    @Query("""
        UPDATE users SET 
        isDarkMode = :isDarkMode,
        notificationsEnabled = :notificationsEnabled,
        reminderTime = :reminderTime,
        weeklyStepsGoal = :weeklyStepsGoal,
        weeklyWorkoutsGoal = :weeklyWorkoutsGoal,
        weeklySleepHoursGoal = :weeklySleepHoursGoal,
        weeklyWaterIntakeGoal = :weeklyWaterIntakeGoal,
        shareHealthData = :shareHealthData,
        shareLocation = :shareLocation,
        shareActivityData = :shareActivityData,
        allowAnalytics = :allowAnalytics
        WHERE id = :userId
    """)
    suspend fun updatePreferences(
        userId: String,
        isDarkMode: Boolean,
        notificationsEnabled: Boolean,
        reminderTime: String,
        weeklyStepsGoal: Int,
        weeklyWorkoutsGoal: Int,
        weeklySleepHoursGoal: Int,
        weeklyWaterIntakeGoal: Int,
        shareHealthData: Boolean,
        shareLocation: Boolean,
        shareActivityData: Boolean,
        allowAnalytics: Boolean
    )
} 