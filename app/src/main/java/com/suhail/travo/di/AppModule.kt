package com.suhail.travo.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.suhail.travo.api.BackendAPI
import com.suhail.travo.roomDB.Database
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit() : Retrofit = Retrofit.Builder()
        .baseUrl(BackendAPI.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideBackendAPI (retrofit: Retrofit) : BackendAPI = retrofit.create(BackendAPI::class.java)


    @Provides
    @Singleton
    fun provideDatabase(app:Application) : Database =
        Room.databaseBuilder(app,Database::class.java,"user_db")
            .build()

    @Singleton
    @Provides
    fun provideSharedPreference(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("preferences_name", Context.MODE_PRIVATE)
    }

}