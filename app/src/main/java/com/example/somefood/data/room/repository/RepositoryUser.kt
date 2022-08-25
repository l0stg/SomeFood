package com.example.somefood.data.room.repository

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import com.example.somefood.data.model.UserModel
import com.example.somefood.data.model.UserProfileModel
import com.example.somefood.data.model.UserTypes
import com.example.somefood.data.room.dao.UserDao
import com.example.somefood.di.PREFERENCES_FILE_KEY
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.io.File
import java.util.*

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

    suspend fun increaseOrdersByClient(userId: Int) =
        myDao.increaseOrdersByClient(userId)

    suspend fun increaseOrdersByCreator(userId: Int) =
        myDao.increaseOrderByCreator(userId)

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

    suspend fun updateUserPhoto(userID: Int, profilePhoto: String) {
        myDao.updateUserPhoto(userID, profilePhoto)
    }

    fun writeToInternalStoragePhoto(context: Context?, newUri: Uri?, oldUri: String): String {
        val byteArray = newUri?.let { context?.contentResolver?.openInputStream(it)?.readBytes() }
        val folder = File(context?.filesDir, "Avatars")
        folder.mkdirs()
        File(oldUri).delete()
        val file = File(folder, "${UUID.randomUUID()}")
        if (byteArray != null) {
            file.writeBytes(byteArray)
        }
        return Uri.parse(file.absolutePath).toString()
    }

}