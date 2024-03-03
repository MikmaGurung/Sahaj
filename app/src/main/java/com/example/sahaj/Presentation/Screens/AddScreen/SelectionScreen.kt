package com.example.sahaj.Presentation.Screens.AddScreen

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import androidx.navigation.NavController
import com.example.sahaj.Presentation.Components.DatePicker
import com.example.sahaj.Presentation.Components.SelectionScreenAddItem
import com.example.sahaj.Feature.Navigation.Screen
import com.example.sahaj.Util.AddOrUpdateMode
import com.example.sahaj.Util.SahajEvent
import com.example.sahaj.Util.SahajState
import com.example.sahaj.Presentation.ViewModel.SahajViewModel
import com.vanpra.composematerialdialogs.rememberMaterialDialogState

@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun SelectionScreen(
    onEvent :(SahajEvent) -> Unit,
    navController: NavController,
    viewModel: SahajViewModel,
    state : SahajState
){
    DisposableEffect(Unit){
        onDispose {
            if (state.mode == AddOrUpdateMode.ADD) viewModel.changeMode(AddOrUpdateMode.UPDATE) else
                viewModel.changeMode(AddOrUpdateMode.ADD)
        }
    }
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current

    var tiitle by rememberSaveable {
        mutableStateOf("")
    }
    var isErrorUnits by rememberSaveable {
        mutableStateOf(false)
    }
    var units : String by rememberSaveable {
        mutableStateOf(state.units)
    }

    var remarks : String by rememberSaveable {
        mutableStateOf(state.remarks)
    }

    var Date by rememberSaveable {
        mutableStateOf(state.date)
    }
    LaunchedEffect(Unit){
    if (state.mode == AddOrUpdateMode.UPDATE){
        units = state.selectedItem.units
        remarks = state.selectedItem.remarks
        Date = state.selectedItem.date
    }else{
        units = state.units
        remarks = state.remarks
        Date = state.date
    }
    }

    val modifier = Modifier

    val focusManager = LocalFocusManager.current

    DatePicker(onEvent = viewModel::onEvent, state = state, date = {Date = it})
    Column(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures {
                    keyboardController?.hide()
                    focusManager.clearFocus()
                }
            }
        ,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.padding(10.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ){
        SelectionScreenAddItem(
            modifier
                .padding(start = 33.dp),
            onEvent,
            state,
            title = {tiitle = it}
            )
        }
          OutlinedTextField(
              modifier = Modifier
                  .clickable {
                      makeToast(context)
                  },
                value =units ,
            onValueChange = {
                if (it.isDigitsOnly()){
                if (it.length<10) {
                   units = it
                }
                    isErrorUnits = validate(it)
                            }},
            label = {Text(text = "Units")
            },
            singleLine = true,
              isError = isErrorUnits,
              supportingText = {
                  if (isErrorUnits)
                      Text(
                          modifier = Modifier.fillMaxWidth(),
                          text = "Enter Units",
                          color = MaterialTheme.colorScheme.error
                      )
              },
              keyboardOptions = KeyboardOptions(
                  keyboardType = KeyboardType.Number,
                  imeAction = ImeAction.Done),
              keyboardActions = KeyboardActions(
                  onDone = {
                      focusManager.clearFocus()
                  }
              )
        )

        OutlinedTextField(
            value = remarks,
            onValueChange ={
                if (it.length<10) {
                    remarks = it
                }
            },
            singleLine = true,
            label = { Text(text = "Remarks")},
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
        )

        Spacer(modifier = Modifier.padding(10.dp))

            OutlinedTextField(
                value = Date,
                onValueChange = {
                },
                singleLine = true,
                 trailingIcon = { IconButton(onClick = {
                     makeToast(context)
                     onEvent(SahajEvent.showDatePicker(true))
                 }) {
                     Icons.Default.DateRange
                 }},
                readOnly = true,
                label = { Text(text ="Date")}
                )
            Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ){
            Button(
                modifier = Modifier
                    .width(280.dp),
                onClick = {
                    isErrorUnits  = validate(units)
                    onEvent(SahajEvent.isError(validate(state.title)))
                    if (!isErrorUnits  && units.isNotEmpty()   && !state.isError) {
                        onEvent(SahajEvent.setDate(Date))
                        onEvent(SahajEvent.SetTitle(tiitle))
                        onEvent(SahajEvent.SetAmount(convertToAmount(units)))
                        onEvent(SahajEvent.SetUnits(units))
                        onEvent(SahajEvent.SetRemarks(remarks))
                        navController.navigate(Screen.MainScreen.route)
                        makeToast(context = context)
                        onEvent(SahajEvent.isError(false))
                        focusManager.clearFocus(true)
                        viewModel.onSumbit()
            }},
                shape = RoundedCornerShape(15.dp)
            ) {
                val text = if(state.mode == AddOrUpdateMode.UPDATE) "Update" else "Add"
                Text(
                    text = text,
                    fontSize = 20.sp)
            }
        }
    }
}


fun validate(text: String?) : Boolean{
    return text.isNullOrEmpty()
}

fun makeToast(context : Context) {
    Toast.makeText(context,"Saved Successfully",Toast.LENGTH_LONG).show()
}

fun convertToAmount(units : String) : Int {
    return units.toInt() * 15
}

