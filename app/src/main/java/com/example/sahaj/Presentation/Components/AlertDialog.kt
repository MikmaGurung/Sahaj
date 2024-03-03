package com.example.sahaj.Presentation.Components

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.sahaj.Util.SahajEvent
import com.example.sahaj.Util.SahajState

@Composable
fun AlertDialog(
    onEvent: (SahajEvent) -> Unit,
    state: SahajState
){
    androidx.compose.material3.AlertDialog(
        title = { Text(text ="Delete Item" ) },
        onDismissRequest = {
            onEvent(SahajEvent.ShowDialog(false))
        },
        dismissButton = {
            Button(onClick = {
                onEvent(SahajEvent.ShowDialog(false))
            }) {
                Text(text = "Cancel")
            }
        },
        confirmButton = {
            Button(onClick = {
                onEvent(SahajEvent.DeleteItem(state.selectedItem))
                onEvent(SahajEvent.ShowDialog(false))
            }) {
                Text(text = "Delete")
            }
        },
        text = { Text(text = "Delete for sure") }
    )
}

