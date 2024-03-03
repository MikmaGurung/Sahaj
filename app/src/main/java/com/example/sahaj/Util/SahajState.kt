package com.example.sahaj.Util

import com.example.sahaj.Data.Model.Sahaj


data class SahajState(
    val sahaj : List<Sahaj> = emptyList(),
    val date : String = "",
    val title : String = "",
    val remarks : String = "",
    val amount : Int? = null,
    val units : String= "",
    val paid : Boolean = false,
    val addClicked : Boolean? = false,
    val id : Int = 0,
    val selectedItem : Sahaj = Sahaj(
        id = 1,
        amount = null,
        paid = false,
        remarks = "",
        title = "",
        units= "",
        date = ""
    ),
    val titleForHome : String = "",
    var isError : Boolean = false,
    val showDialog : Boolean = false,
    val showDatePicker : Boolean = false,
    val mode : AddOrUpdateMode = AddOrUpdateMode.ADD
 )