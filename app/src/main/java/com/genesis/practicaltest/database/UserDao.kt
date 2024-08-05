package com.genesis.practicaltest.database


import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
abstract class UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun addUser(user: User)

    @Query("Select * from 'users'")
    abstract fun getAllUsers(): Flow<List<User>>

    @Update
    abstract suspend fun updateUser(user: User)

    @Delete
    abstract suspend fun deleteUser(user: User)

    @Query("Select * from 'users' where id=:id")
    abstract fun getUserById(id: Int): Flow<User>
}