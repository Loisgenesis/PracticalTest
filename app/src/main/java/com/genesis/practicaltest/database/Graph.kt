package com.genesis.practicaltest.database

import android.content.Context
import androidx.room.Room

object Graph {
    lateinit var database: UserDatabase

    val userRepository by lazy {
        UserRepository(userDao = database.userDao())
    }

    fun provide(context: Context) {
        database = Room.databaseBuilder(context, UserDatabase::class.java, "user.db").build()
    }
}