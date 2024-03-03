package com.example.sahaj.Data.Di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import  com.example.sahaj.Data.Di.Database
import com.example.sahaj.Data.repositories.SahajRepository
import dagger.Provides
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Module {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    )=
         Room.databaseBuilder(
            context,
            Database::class.java,
            "SahajDatabase"
        )
             .fallbackToDestructiveMigration()
             .build()


    @Provides
    @Singleton
    fun provideDao(database: Database) = database.Dao()
}