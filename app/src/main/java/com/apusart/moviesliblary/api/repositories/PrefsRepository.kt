package com.apusart.moviesliblary.api.repositories

import android.content.Context
import javax.inject.Inject
import javax.inject.Singleton

//interface IPrefsRepository {
//    val PREFS_NAME: String
//
//    fun getSession(): String?
//    fun saveSession(sessionId: String)
//
//}
//
//@Singleton
//class PrefsRepository @Inject constructor(
//    private val context: Context
//): IPrefsRepository {
//    override val PREFS_NAME: String = "com.apusart.movieslibrary.PrefsRepository"
//    override fun getSession(): String? {
//        val s = context.getSharedPreferences(
//            PREFS_NAME, Context.MODE_PRIVATE)
//
//        return s.getString("session_id", null)
//    }
//
//    override fun saveSession(sessionId: String) {
//        val s = context.getSharedPreferences(
//            PREFS_NAME, Context.MODE_PRIVATE)
//
//        val editor = s?.edit()
//
//        editor?.putString("session_id", sessionId)
//        editor?.apply()
//    }
//
//}