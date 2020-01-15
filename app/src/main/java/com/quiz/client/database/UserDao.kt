package com.quiz.client.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    fun selectUser():List<User>

    @Insert
    fun insertUser(user: User)

    @Update
    fun updateUser(user:User)
}