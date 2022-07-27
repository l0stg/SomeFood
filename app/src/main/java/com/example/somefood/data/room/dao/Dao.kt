package com.example.somefood.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.somefood.data.model.UserModel
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {

    // Для регистрации
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUser(newUser: UserModel)

    // Для авторизации
    @Query("SELECT * FROM user_table WHERE email LIKE :email  AND password LIKE :password ")
    fun checkAuth(email: String, password: String): Flow<List<UserModel>>

    // для проверки при регистрации по емэйлу
    @Query("SELECT * FROM user_table WHERE email LIKE :email ")
    fun checkRegistration(email: String): Flow<List<UserModel>>


}