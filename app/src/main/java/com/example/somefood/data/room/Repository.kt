package com.example.somefood.data.room

import com.example.somefood.data.model.UserModel
import com.example.somefood.data.room.dao.UserDao
import kotlinx.coroutines.flow.Flow

class Repository(
    private val myDao: UserDao
) {
    suspend fun addUser(newUser: UserModel){
        myDao.addUser(newUser)
    }

    fun checkAuth(email: String, password: String): Flow<List<UserModel>> =
        myDao.checkAuth(email = email, password = password)

    fun checkRegistration(email: String): Flow<List<UserModel>> =
        myDao.checkRegistration(email)

}