package com.example.sahaj.Util

import com.example.sahaj.Data.Model.Sahaj

sealed class SahajEvent {
    data class SetTitle(val title : String) : SahajEvent()
    data class SetRemarks(val remarks : String) : SahajEvent()
    data class SetUnits(val units :String) : SahajEvent()
    data class SetAmount(val amount : Int?) : SahajEvent()
    data class DeleteItem(val sahaj: Sahaj) : SahajEvent()
    data class onDeleteClicked(val sahaj: Sahaj) : SahajEvent()
    data class setDate (val date : String) : SahajEvent()
     data class onAddIconClicked(val clicked : Boolean?): SahajEvent()
    data class isError(val isError : Boolean) :SahajEvent()
    data class ShowDialog(val showDialog : Boolean) : SahajEvent()
    data class selectedItem(val sahaj: Sahaj) : SahajEvent()
    data class setTitleForHome(val title : String) : SahajEvent()
    data class showDatePicker(val show : Boolean) :SahajEvent()
}