package com.example.somefood.data.room.repository

import android.content.SharedPreferences
import com.example.somefood.data.model.UserModel
import com.example.somefood.data.room.dao.UserDao
import kotlinx.coroutines.flow.Flow

class RepositoryUser(
    private val myDao: UserDao,
    private val mySharedPreferences: SharedPreferences
) {
    suspend fun addUser(newUser: UserModel){
        myDao.addUser(newUser)
    }

    fun getUserForID(userID: Int): Flow<UserModel> =
        myDao.getUserForID(userID)

    fun checkAuth(email: String, password: String): Flow<UserModel> =
         myDao.checkAuth(email = email, password = password)


    fun checkRegistration(email: String): Flow<List<UserModel>> =
        myDao.checkRegistration(email)

    fun saveUserID(id: Int){
        mySharedPreferences.edit().putInt("preferences", id).apply()
    }

    fun getUserID(): Int {
        val userID: Int
        if(mySharedPreferences.contains("preferences"))
            userID = mySharedPreferences.getInt("preferences", 0)
        else
            userID = -1
        return userID
    }
}