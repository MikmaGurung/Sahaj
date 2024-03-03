package com.example.sahaj.Presentation.Screens.HomeScreen


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.navigation.NavController
import com.example.sahaj.Presentation.Components.AlertDialog
import com.example.sahaj.Feature.Navigation.Screen
import com.example.sahaj.Util.SahajEvent
import com.example.sahaj.Util.SahajState
import com.example.sahaj.Presentation.ViewModel.SahajViewModel
import   com.example.sahaj.Presentation.Components.List
import com.example.sahaj.Util.AddOrUpdateMode

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun HomeScreen(
    onEvent: (SahajEvent) -> Unit,
    state : SahajState,
    navController: NavController,
    viewModel: SahajViewModel,
) {
    val modifier = Modifier
    val scrollBehaviour = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehaviour.nestedScrollConnection),
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ), title = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(text = "Sahaj")
                    }
                },
                scrollBehavior = scrollBehaviour
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                onEvent(SahajEvent.onAddIconClicked(true))
                navController.navigate(Screen.SelectionScreen.route)
                viewModel.changeMode(AddOrUpdateMode.ADD)
            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Button")
            }
        }

    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if (state.showDialog){
                AlertDialog(onEvent = onEvent,state)
            }
            else{
          List(state = state, modifier = modifier,onEvent,
                onItemClicked = {
                    navController.navigate(Screen.ItemScreen.route)
                    onEvent(SahajEvent.selectedItem(it))
                },
                viewModel = viewModel
            )
            }
        }
    }
}



