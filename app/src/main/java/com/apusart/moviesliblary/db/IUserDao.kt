package com.apusart.moviesliblary.db

import androidx.room.*

@Dao
interface IUserDao {
    @Query("SELECT * FROM user WHERE uid = 1 LIMIT 1")
    suspend fun getUser(): List<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)
}