package com.lifesyncpro.app.domain.repository

import com.lifesyncpro.app.domain.model.User
import com.lifesyncpro.app.domain.model.HealthProfile
import com.lifesyncpro.app.domain.model.UserPreferences
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun createUser(user: User): Result<User>
    suspend fun getUserById(id: String): Result<User>
    suspend fun updateUser(user: User): Result<User>
    suspend fun deleteUser(id: String): Result<Boolean>
    suspend fun updateHealthProfile(userId: String, healthProfile: HealthProfile): Result<User>
    suspend fun updatePreferences(userId: String, preferences: UserPreferences): Result<User>
    fun observeUser(userId: String): Flow<User>
    suspend fun signIn(email: String, password: String): Result<User>
    suspend fun signUp(email: String, password: String, displayName: String): Result<User>
    suspend fun signOut(): Result<Boolean>
    suspend fun resetPassword(email: String): Result<Boolean>
    suspend fun updatePassword(oldPassword: String, newPassword: String): Result<Boolean>
    suspend fun updateEmail(newEmail: String, password: String): Result<Boolean>
    suspend fun verifyEmail(): Result<Boolean>
    suspend fun isUserSignedIn(): Boolean
    suspend fun getCurrentUser(): Result<User>
} 