package com.genesis.practicaltest.database

import kotlinx.coroutines.flow.Flow

class UserRepository(private val userDao: UserDao) {

   suspend fun addAUser(wish: User){
        userDao.addUser(wish)
    }

    fun getUsers(): Flow<List<User>> = userDao.getAllUsers()

    fun getUserById(id: Int): Flow<User>{
        return userDao.getUserById(id)
    }

    suspend fun updateUser(user: User){
        userDao.updateUser(user)
    }

    suspend fun deleteUser(user: User){
        userDao.deleteUser(user)
    }
}