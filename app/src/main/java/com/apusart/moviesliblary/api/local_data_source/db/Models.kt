package com.apusart.moviesliblary.api.local_data_source.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "username") val username: String,
    @ColumnInfo(name = "session_id") val sessionId: String,
    @ColumnInfo(name = "id") val id: Int
)