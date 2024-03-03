package com.example.sahaj.Presentation.Components

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import com.example.sahaj.Presentation.Screens.AddScreen.validate
import com.example.sahaj.Util.SahajEvent
import com.example.sahaj.Util.SahajState
import com.example.sahaj.Presentation.ViewModel.SahajViewModel


@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun HomeScreenAddItem(
    modifier: Modifier,
    onEvent : (SahajEvent)-> Unit,
    state: SahajState,
    viewModel: SahajViewModel
){
    val focusManager = LocalFocusManager.current
    var expanded by remember {
        mutableStateOf(false)
    }
    var item by remember {
        mutableStateOf("")
    }
    val context = LocalContext.current
    val items = arrayOf("Hotel","New Store","Book Store","Cloth Store","All")
    Box(
        modifier = modifier
            .fillMaxWidth()
    ) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded} ) {
            TextField(
                value = state.titleForHome,
                onValueChange = {
                    onEvent(SahajEvent.setTitleForHome(it))
                    onEvent(SahajEvent.isError(validate(it)))
                },
                label = { Text(text = "Store Name") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)},
                modifier = modifier.menuAnchor(),
                singleLine = true,
                   keyboardActions = KeyboardActions (onDone = {
                       focusManager.clearFocus()
                   }
                   ),
                isError = state.isError,
                supportingText = {
                    if (state.isError)
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = "Enter Shop Name",
                            color = MaterialTheme.colorScheme.error)
                }
            )

            val filteredOptions =
                items.filter {
                    it.contains(state.titleForHome,ignoreCase = true)
                }
            if (filteredOptions.isNotEmpty()){
                ExposedDropdownMenu(
                    expanded =  expanded,
                    onDismissRequest = {
                        expanded = !expanded
                    }) {
                    filteredOptions.forEach { items ->
                        DropdownMenuItem(
                            text = { Text(text = items) },
                            onClick = {
                                item = items
                                onEvent(SahajEvent.isError(validate(items)))
                                focusManager.clearFocus(true)
                                onEvent(SahajEvent.setTitleForHome(item))
                                expanded = false
                                Toast.makeText(context,items, Toast.LENGTH_SHORT).show()
                               viewModel.searchDatabase(item)
                            })
                    }
                }
            }
        }

    }
}
