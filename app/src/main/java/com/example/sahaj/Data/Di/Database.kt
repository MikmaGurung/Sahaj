package com.example.sahaj.Data.Di

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.sahaj.Data.Dao.SahajDao
import com.example.sahaj.Data.Model.Sahaj

@Database(
    entities = [
        Sahaj::class
    ],
    version = 5
)
abstract class Database : RoomDatabase() {
    abstract fun Dao() : SahajDao
}