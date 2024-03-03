package com.example.sahaj.Data.repositories

import com.example.sahaj.Data.Dao.SahajDao
import com.example.sahaj.Data.Model.Sahaj
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class SahajRepository @Inject constructor(
    private val dao : SahajDao
){

    val getAllTasks : Flow<List<Sahaj>> = dao.getAllTasks()

    fun getSelectedItem(itemId : Int) : Flow<Sahaj>{
        return dao.getSelectedTask(itemId)
    }

    suspend fun addTask(sahaj: Sahaj) {
         dao.insertTask(sahaj)
    }

    suspend fun updateTask(sahaj: Sahaj){
         dao.updateTask(sahaj )
    }

    suspend fun deleteTask(sahaj: Sahaj){
         dao.deleteTask(sahaj)
    }
    fun searchDatabase(searchQuery : String) :Flow<List<Sahaj>>{
        return dao.searchDatabase(searchQuery)}


   suspend fun deleteAll(){
        dao.deleteAll()
    }
}