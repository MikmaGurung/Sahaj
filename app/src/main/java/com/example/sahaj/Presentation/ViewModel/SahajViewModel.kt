package com.example.sahaj.Presentation.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sahaj.Data.Model.Sahaj
import com.example.sahaj.Util.SahajEvent
import com.example.sahaj.Util.SahajState
import com.example.sahaj.Util.AddOrUpdateMode
import com.example.sahaj.Util.PopulatedSahaj
import com.example.sahaj.Data.repositories.SahajRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SahajViewModel @Inject constructor(
    private val repository: SahajRepository
):ViewModel(){
    private val _state = MutableStateFlow(SahajState())
    val addstate:StateFlow<SahajState> = _state

    fun onEvent(event: SahajEvent) {
        when (event) {
            is SahajEvent.DeleteItem -> {
                viewModelScope.launch {
                    repository.deleteTask(event.sahaj)
                }
            }

            is SahajEvent.SetAmount -> {
                _state.update {
                    it.copy(
                        amount = event.amount
                    )
                }
            }

            is SahajEvent.SetRemarks -> {
                _state.update {
                    it.copy(
                        remarks = event.remarks
                    )
                }
            }

            is SahajEvent.SetTitle -> {
                _state.update {
                    it.copy(
                        title = event.title
                    )
                }
            }

            is SahajEvent.SetUnits -> {
                _state.update {
                    it.copy(
                        units = event.units,

                        )
                }
            }

            is SahajEvent.setDate -> {
                _state.update {
                    it.copy(
                        date = event.date
                    )
                }
            }


            is SahajEvent.onDeleteClicked -> {
                _state.update {
                    it.copy(
                        selectedItem = event.sahaj
                    )
                }
            }

            is SahajEvent.isError -> {
                _state.update {
                    it.copy(
                        isError = event.isError
                    )
                }
            }


            is SahajEvent.ShowDialog -> {
                _state.update {
                    it.copy(
                        showDialog = event.showDialog
                    )
                }
            }

            is SahajEvent.onAddIconClicked -> {
                _state.update {
                    it.copy(
                        addClicked = event.clicked,
                    )
                }
            }

            is SahajEvent.setTitleForHome ->{
                _state.update {
                    it.copy(
                        titleForHome = event.title
                    )
                }
            }

            is SahajEvent.selectedItem -> {
                _state.update {
                    it.copy(
                        selectedItem = event.sahaj
                    )
                }
            }
            is SahajEvent.showDatePicker ->{
                _state.update {
                    it.copy(
                        showDatePicker = event.show
                    )
                }
            }
        }
    }
    private fun updateTask(){
         val title = addstate.value.title
         val remarks = addstate.value.remarks
         val amount = addstate.value.amount
         val units = addstate.value.units
         val paid = addstate.value.paid
         val date = addstate.value.date
         val id = addstate.value.selectedItem.id
         val item = Sahaj(
             id = id,
             title = title,
             remarks = remarks,
             amount = amount,
             units = units,
             paid = paid,
             date = date
         )
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateTask(item)
        }
         _state.update {
             it.copy(
                 id = 0,
                 title =  "",
                 remarks = "",
                 amount = null,
                 units = "",
                 paid = false,
                 date = ""
             )
         }
    }
    private fun addTask(){
        val title = addstate.value.title
        val remarks = addstate.value.remarks
        val amount = addstate.value.amount
        val units = addstate.value.units
        val paid = addstate.value.paid
        val date = addstate.value.date

        val item = Sahaj(
            title = title,
            remarks = remarks,
            amount = amount,
            units = units,
            paid = paid,
            date = date
        )
        viewModelScope.launch {
            repository.addTask(
                item
            )
        }
        _state.update {
            it.copy(
                title = "",
                remarks = "",
                amount = null,
                units = "",
                date = ""
            )
        }
    }

    fun onSumbit(){
        viewModelScope.launch {
            try {
                if (_state.value.mode == AddOrUpdateMode.ADD){
                    addTask()
                }
                else{
                    updateTask()
                }
            }catch (e: Exception){
                print(e)
            }
        }
    }

    init {
        searchDatabase("All")
    }
    fun searchDatabase(searchQuery : String ){
        try {
            viewModelScope.launch {
                repository.searchDatabase(searchQuery)
                    .collect{searchTasks->
                        println("collected tasks $searchTasks")
                       _state.update {
                           it.copy(
                               sahaj = searchTasks
                           )
                       }
                    }
            }
        }catch (e: Exception){
            print("error $e")
        }
    }

    fun changeMode(mode : AddOrUpdateMode){
        viewModelScope.launch {
            when(_state.value.mode){
                AddOrUpdateMode.ADD ->{
                    _state.update {
                        it.copy(mode = mode)
                    }
                }
                AddOrUpdateMode.UPDATE ->{
                    _state.update {
                        it.copy(
                            mode = mode,
                            )
                    }
                }
            }
        }

    }
}