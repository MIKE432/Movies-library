package com.apusart.moviesliblary.di

import android.app.Application
import com.apusart.moviesliblary.api.local_data_source.db.MoviesLibraryDatabase
import com.apusart.moviesliblary.api.remote_data_source.IMovieLibraryService
import com.apusart.moviesliblary.tools.Defaults
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    fun provideGson(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {

        return HttpLoggingInterceptor().apply {
            level =  HttpLoggingInterceptor.Level.BODY
        }
    }


    @Provides
    fun provideClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    @Singleton
    @Provides
    fun provideRoomDatabase(): MoviesLibraryDatabase {
        return MoviesLibraryDatabase.db
    }

    @Singleton
    @Provides
    fun provideNormalRetrofitService(client: OkHttpClient, gsonCorverterFactory: GsonConverterFactory): IMovieLibraryService {

        return Retrofit.Builder()
            .baseUrl(Defaults.baseUrl)
            .addConverterFactory(gsonCorverterFactory)
            .client(client)
            .build()
            .create(IMovieLibraryService::class.java)
    }

}