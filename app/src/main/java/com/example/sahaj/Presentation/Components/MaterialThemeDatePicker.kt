package com.example.sahaj.Presentation.Components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.DatePicker
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import com.example.sahaj.Util.SahajEvent
import com.example.sahaj.Util.SahajState
import java.text.SimpleDateFormat
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePicker(
    onEvent :(SahajEvent) -> Unit,
    state: SahajState,
    date :(String) -> Unit){
    val datePickerState = rememberDatePickerState()
    if (state.showDatePicker){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement =Arrangement.Center
    ) {
        DatePickerDialog(
            confirmButton = {
                TextButton(onClick = {
                    val selectedDate = Calendar.getInstance().apply {
                        timeInMillis = datePickerState.selectedDateMillis!!
                    }
                    val localDate = selectedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()

                    val dateFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy", Locale.getDefault())
                    val formattedDate = dateFormatter.format(localDate)
                    if (selectedDate.after(Calendar.getInstance())){
                        onEvent(SahajEvent.setDate(formattedDate))
                        date(formattedDate)
                    }
                    onEvent(SahajEvent.showDatePicker(false))
                }) {
                    Text(text = "Ok")
                }
            },
               dismissButton = {
                  Button(onClick = {
                    onEvent(SahajEvent.showDatePicker(false))
                }) {
                    Text(text = "Dismiss")
                }
            },
            onDismissRequest = {
                onEvent(SahajEvent.showDatePicker(false))
            }
        )
        {
            DatePicker(state = datePickerState)
        }
    }}
}