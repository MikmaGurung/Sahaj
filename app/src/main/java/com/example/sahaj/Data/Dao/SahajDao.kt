package com.example.sahaj.Data.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.sahaj.Data.Model.Sahaj
import kotlinx.coroutines.flow.Flow


@Dao
interface SahajDao {

    @Query("SELECT * FROM Sahaj")
    fun getAllTasks () : Flow<List<Sahaj>>

    @Query("SELECT * FROM Sahaj WHERE id = :id")
    fun getSelectedTask(id : Int) : Flow<Sahaj>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertTask(sahaj: Sahaj)

    @Delete
    suspend fun deleteTask(sahaj: Sahaj)

    @Query("DELETE FROM Sahaj")
    suspend fun deleteAll()

    @Update
    suspend fun updateTask(sahaj: Sahaj)

    @Query("SELECT * FROM sahaj WHERE title LIKE :searchQuery OR :searchQuery = 'All'")
    fun searchDatabase(searchQuery : String) : Flow<List<Sahaj>>
}