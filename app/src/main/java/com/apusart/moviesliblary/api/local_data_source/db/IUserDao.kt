package com.apusart.moviesliblary.api.local_data_source.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface IUserDao {
    @Query("SELECT * FROM user WHERE uid = :uid LIMIT 1")
    suspend fun getUser(uid: Int): User?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(user: User)

    @Query("DELETE FROM User WHERE uid = :id")
    suspend fun deleteUser(id: Int): Int
}