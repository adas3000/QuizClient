package com.quiz.client.database

import androidx.room.Database

@Database(entities = arrayOf(User::class),version = 1)
abstract class AppDatabase {
    abstract fun userDao():UserDao
}