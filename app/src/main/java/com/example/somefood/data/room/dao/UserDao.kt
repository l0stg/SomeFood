package com.example.somefood.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.somefood.data.model.UserModel
import com.example.somefood.data.model.UserProfileModel
import com.example.somefood.data.model.UserTypes
import kotlinx.coroutines.flow.Flow

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

    @Query("SELECT * FROM user_table WHERE userUID LIKE :userID")
    suspend fun observeUserById(userID: Int): UserModel

    @Query("UPDATE user_table SET types = :newTypes WHERE userUID = :userId")
    suspend fun updateUserTypes(userId: Int, newTypes: UserTypes)

    @Query("UPDATE user_table SET photoProfile = :newPhoto WHERE userUID = :userId")
    suspend fun updateUserPhoto(userId: Int, newPhoto: String)

    @Query("UPDATE user_table SET description = :newDescription WHERE userUID = :userId")
    suspend fun updateUserDescription(userId: Int, newDescription: String)

    @Query("UPDATE user_table SET ordersAsClient = ordersAsClient + 1 WHERE userUID =:userId")
    suspend fun increaseOrdersByClient(userId: Int)

    @Query("UPDATE user_table SET ordersAsCreator = ordersAsCreator + 1 WHERE userUID =:userId")
    suspend fun increaseOrderByCreator(userId: Int)

    @Query("SELECT email, types, photoProfile, description," +
            " ordersAsClient, ordersAsCreator, AVG(starForClient) as starForClient, AVG(starForCreator) as starForCreator" +
            " FROM user_table LEFT JOIN user_rating ON userUID == userid WHERE userUID == :userId")
    fun observeUserByIdFlow(userId: Int): Flow<UserProfileModel>


}