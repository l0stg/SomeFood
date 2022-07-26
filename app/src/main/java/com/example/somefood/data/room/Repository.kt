package com.example.somefood.data.room

import com.example.somefood.data.model.UserModel
import com.example.somefood.data.room.dao.Dao
import kotlinx.coroutines.flow.Flow

class Repository(
    private val myDao: Dao
) {
    suspend fun addUser(){
        val defaultUser = UserModel(0,"max","111")
        myDao.addUser(defaultUser)
    }

    fun checkAuth(email: String, password: String): Flow<List<UserModel>> =
        myDao.searchUsers(email = email, password = password)

}