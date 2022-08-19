package com.example.somefood.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.somefood.data.model.UserModel
import com.example.somefood.data.model.UserTypes

@Dao
interface UserDao {

    // Для регистрации
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUser(newUser: UserModel): Long

    // Для авторизации
    @Query("SELECT * FROM user_table WHERE email LIKE :email  AND password LIKE :password ")
    suspend fun checkAuth(email: String, password: String): UserModel

    // Для проверки при регистрации по емэйлу
    @Query("SELECT * FROM user_table WHERE email LIKE :email ")
    suspend fun checkRegistration(email: String): List<UserModel>

    @Query("SELECT * FROM user_table WHERE id LIKE :userID")
    suspend fun observeUserById(userID: Int): UserModel

    @Query("UPDATE user_table SET types = :newTypes WHERE id = :userId")
    suspend fun updateUserTypes(userId: Int, newTypes: UserTypes)

    @Query("UPDATE user_table SET description = :newDescription WHERE id = :userId")
    suspend fun updateUserDescription(userId: Int, newDescription: String)

    @Query("UPDATE user_table SET orderByClient = orderByClient + 1 WHERE id =:userId")
    suspend fun updateOrderByClient(userId: Int)

    @Query("UPDATE user_table SET orderByCreator = orderByCreator + 1 WHERE id =:userId")
    suspend fun updateOrderByCreator(userId: Int)



}