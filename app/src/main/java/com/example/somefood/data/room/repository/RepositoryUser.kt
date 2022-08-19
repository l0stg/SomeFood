package com.example.somefood.data.room.repository

import android.content.ClipDescription
import android.content.SharedPreferences
import android.net.Uri
import com.example.somefood.data.model.UserModel
import com.example.somefood.data.model.UserProfileModel
import com.example.somefood.data.model.UserTypes
import com.example.somefood.data.room.dao.UserDao
import com.example.somefood.di.PREFERENCES_FILE_KEY
import kotlinx.coroutines.flow.Flow

class RepositoryUser(
    private val myDao: UserDao,
    private val mySharedPreferences: SharedPreferences
) {
    suspend fun addUser(newUser: UserModel): Long =
        myDao.addUser(newUser)

    suspend fun observeUserById(userID: Int): UserModel =
        myDao.observeUserById(userID)

    suspend fun checkAuth(email: String, password: String): UserModel =
        myDao.checkAuth(email = email, password = password)

    suspend fun checkRegistration(email: String): List<UserModel> =
        myDao.checkRegistration(email)

    suspend fun updateUserType(userId: Int, types: UserTypes) =
        myDao.updateUserTypes(userId, types)

    suspend fun updateUserDescription(userId: Int, newDescription: String) =
        myDao.updateUserDescription(userId, newDescription)

    suspend fun updateOrderByClient(userId: Int) =
        myDao.updateOrderByClient(userId)

    suspend fun updateOrderByCreator(userId: Int) =
        myDao.updateOrderByCreator(userId)

    fun observeUserByIdFlow(userId: Int): Flow<UserProfileModel> =
        myDao.observeUserByIdFlow(userId)

    fun saveUserID(id: Int) {
        mySharedPreferences.edit().putInt(PREFERENCES_FILE_KEY, id).apply()
    }

    fun getUserID(): Int {
        val userID: Int
        if (mySharedPreferences.contains(PREFERENCES_FILE_KEY))
            userID = mySharedPreferences.getInt(PREFERENCES_FILE_KEY, 0)
        else
            userID = -1
        return userID
    }


}