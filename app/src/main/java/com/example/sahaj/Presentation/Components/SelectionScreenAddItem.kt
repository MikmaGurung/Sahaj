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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import com.example.sahaj.Presentation.Screens.AddScreen.validate
import com.example.sahaj.Util.AddOrUpdateMode
import com.example.sahaj.Util.SahajEvent
import com.example.sahaj.Util.SahajState

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun SelectionScreenAddItem(
    modifier: Modifier,
    onEvent : (SahajEvent)-> Unit,
    state : SahajState,
    title :(String)-> Unit
){

    val focusManager = LocalFocusManager.current
    var expanded by remember {
        mutableStateOf(false)
    }
    var title : String  by rememberSaveable {
        mutableStateOf("")
    }
    val context = LocalContext.current
    val items = arrayOf("Hotel","New Store","Book Store","Cloth Store")
    var isError by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(Unit){
        if (state.mode==AddOrUpdateMode.UPDATE) {
            title = state.selectedItem.title
        title(title)}
    else
        title = state.title
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
    ) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded} ) {
            OutlinedTextField(
                value = title,
                onValueChange = {
                    title = it
                    onEvent(SahajEvent.isError(validate(title)))
                    onEvent(SahajEvent.SetTitle(title))
                },
                label = { Text(text = "Store Name") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)},
                modifier = modifier.menuAnchor(),
                singleLine = true,
                isError = isError,
                supportingText = {
                    if (isError)
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = "Enter Shop Name",
                            color = MaterialTheme.colorScheme.error)
                }, keyboardActions = KeyboardActions(onDone = {
                    focusManager.clearFocus()
                    expanded = false
                })
            )

            val filteredOptions =
                items.filter {
                    it.contains(title,ignoreCase = true)
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
                                title = items
                                isError = validate(items)
                                focusManager.clearFocus(true)
                                onEvent(SahajEvent.SetTitle(title))
                                expanded = false
                                Toast.makeText(context,items, Toast.LENGTH_SHORT).show() })
                    }
                }
            }
        }
    }
}
