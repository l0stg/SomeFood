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
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(newUser: UserModel)

    // Для авторизации
   @Query("SELECT * FROM user_table WHERE email LIKE '%' || :email || '%' AND password LIKE '%' || :password || '%'")
    fun searchUsers(email: String, password: String): Flow<List<UserModel>>

    // для проверки при регистрации по емэйлу
    @Query("SELECT * FROM user_table WHERE email LIKE '%' || :email || '%'")
    fun searchUsersRegistration(email: String): Flow<List<UserModel>>


}