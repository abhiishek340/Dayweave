package com.lifesyncpro.app.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.lifesyncpro.app.data.local.dao.UserDao
import com.lifesyncpro.app.data.local.entity.UserEntity
import com.lifesyncpro.app.domain.model.HealthProfile
import com.lifesyncpro.app.domain.model.User
import com.lifesyncpro.app.domain.model.UserPreferences
import com.lifesyncpro.app.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao,
    private val auth: FirebaseAuth
) : UserRepository {

    override suspend fun createUser(user: User): Result<User> {
        return try {
            userDao.insertUser(UserEntity.fromUser(user))
            Result.success(user)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getUserById(id: String): Result<User> {
        return try {
            val userEntity = userDao.getUserById(id)
            if (userEntity != null) {
                Result.success(userEntity.toUser())
            } else {
                Result.failure(NoSuchElementException("User not found"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun updateUser(user: User): Result<User> {
        return try {
            userDao.updateUser(UserEntity.fromUser(user))
            Result.success(user)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun deleteUser(id: String): Result<Boolean> {
        return try {
            val user = userDao.getUserById(id)
            if (user != null) {
                userDao.deleteUser(user)
                Result.success(true)
            } else {
                Result.failure(NoSuchElementException("User not found"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun updateHealthProfile(
        userId: String,
        healthProfile: HealthProfile
    ): Result<User> {
        return try {
            userDao.updateHealthProfile(
                userId = userId,
                weight = healthProfile.weight,
                height = healthProfile.height,
                birthDate = healthProfile.birthDate,
                gender = healthProfile.gender,
                bloodType = healthProfile.bloodType,
                medicalConditions = healthProfile.medicalConditions,
                allergies = healthProfile.allergies
            )
            getUserById(userId)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun updatePreferences(
        userId: String,
        preferences: UserPreferences
    ): Result<User> {
        return try {
            userDao.updatePreferences(
                userId = userId,
                isDarkMode = preferences.isDarkMode,
                notificationsEnabled = preferences.notificationsEnabled,
                reminderTime = preferences.reminderTime,
                weeklyStepsGoal = preferences.weeklyGoals.steps,
                weeklyWorkoutsGoal = preferences.weeklyGoals.workouts,
                weeklySleepHoursGoal = preferences.weeklyGoals.sleepHours,
                weeklyWaterIntakeGoal = preferences.weeklyGoals.waterIntake,
                shareHealthData = preferences.privacySettings.shareHealthData,
                shareLocation = preferences.privacySettings.shareLocation,
                shareActivityData = preferences.privacySettings.shareActivityData,
                allowAnalytics = preferences.privacySettings.allowAnalytics
            )
            getUserById(userId)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override fun observeUser(userId: String): Flow<User> {
        return userDao.observeUserById(userId).map { userEntity ->
            userEntity?.toUser() ?: throw NoSuchElementException("User not found")
        }
    }

    override suspend fun signIn(email: String, password: String): Result<User> {
        return try {
            val authResult = auth.signInWithEmailAndPassword(email, password).await()
            val firebaseUser = authResult.user
                ?: return Result.failure(Exception("Authentication failed"))

            val userEntity = userDao.getUserByEmail(email)
                ?: UserEntity.fromUser(
                    User(
                        id = firebaseUser.uid,
                        email = email,
                        displayName = firebaseUser.displayName ?: email.substringBefore("@"),
                        photoUrl = firebaseUser.photoUrl?.toString()
                    )
                )

            userDao.upsertUser(userEntity)
            Result.success(userEntity.toUser())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun signUp(
        email: String,
        password: String,
        displayName: String
    ): Result<User> {
        return try {
            val authResult = auth.createUserWithEmailAndPassword(email, password).await()
            val firebaseUser = authResult.user
                ?: return Result.failure(Exception("User creation failed"))

            val profileUpdates = UserProfileChangeRequest.Builder()
                .setDisplayName(displayName)
                .build()
            firebaseUser.updateProfile(profileUpdates).await()

            val user = User(
                id = firebaseUser.uid,
                email = email,
                displayName = displayName
            )
            userDao.insertUser(UserEntity.fromUser(user))
            Result.success(user)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun signOut(): Result<Boolean> {
        return try {
            auth.signOut()
            Result.success(true)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun resetPassword(email: String): Result<Boolean> {
        return try {
            auth.sendPasswordResetEmail(email).await()
            Result.success(true)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun updatePassword(
        oldPassword: String,
        newPassword: String
    ): Result<Boolean> {
        return try {
            val user = auth.currentUser ?: return Result.failure(Exception("User not signed in"))
            val email = user.email ?: return Result.failure(Exception("User email not found"))

            val credential = com.google.firebase.auth.EmailAuthProvider
                .getCredential(email, oldPassword)
            user.reauthenticate(credential).await()

            user.updatePassword(newPassword).await()
            Result.success(true)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun updateEmail(
        newEmail: String,
        password: String
    ): Result<Boolean> {
        return try {
            val user = auth.currentUser ?: return Result.failure(Exception("User not signed in"))
            val oldEmail = user.email ?: return Result.failure(Exception("User email not found"))

            val credential = com.google.firebase.auth.EmailAuthProvider
                .getCredential(oldEmail, password)
            user.reauthenticate(credential).await()

            user.updateEmail(newEmail).await()
            Result.success(true)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun verifyEmail(): Result<Boolean> {
        return try {
            val user = auth.currentUser ?: return Result.failure(Exception("User not signed in"))
            user.sendEmailVerification().await()
            Result.success(true)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun isUserSignedIn(): Boolean {
        return auth.currentUser != null
    }

    override suspend fun getCurrentUser(): Result<User> {
        return try {
            val firebaseUser = auth.currentUser
                ?: return Result.failure(Exception("No user signed in"))

            val userEntity = userDao.getUserById(firebaseUser.uid)
            if (userEntity != null) {
                Result.success(userEntity.toUser())
            } else {
                val newUser = User(
                    id = firebaseUser.uid,
                    email = firebaseUser.email ?: "",
                    displayName = firebaseUser.displayName ?: firebaseUser.email?.substringBefore("@") ?: "",
                    photoUrl = firebaseUser.photoUrl?.toString()
                )
                userDao.insertUser(UserEntity.fromUser(newUser))
                Result.success(newUser)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
} 