package com.example.sahaj.Data.Model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


@Entity
data class Sahaj(
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0,
    val title : String,
    val remarks : String,
    val paid : Boolean,
    val units : String,
    val amount : Int?,
    val date :String =  ""
)
