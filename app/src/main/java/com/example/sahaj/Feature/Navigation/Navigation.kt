package com.example.sahaj.Feature.Navigation

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import com.example.sahaj.Presentation.Screens.HomeScreen.HomeScreen
import com.example.sahaj.Presentation.Screens.ItemScreen.ItemScreen
import com.example.sahaj.Presentation.Screens.AddScreen.SelectionScreen
import com.example.sahaj.Util.AddOrUpdateMode
import com.example.sahaj.Presentation.ViewModel.SahajViewModel

@Composable
fun Navigation(){

    val viewmodel: SahajViewModel = hiltViewModel()
    val state by viewmodel.addstate.collectAsState()
    val navController = rememberNavController()
    val mode = if (state.mode==AddOrUpdateMode.UPDATE) AddOrUpdateMode.ADD else AddOrUpdateMode.UPDATE
    BackHandler(
        onBack = {
            val currentRoute = navController.currentBackStackEntry?.destination?.route
            if (currentRoute == Screen.SelectionScreen.route){
                viewmodel.changeMode(mode)
                navController.popBackStack()
            }
        }
    )
    NavHost(navController = navController, startDestination = Screen.MainScreen.route){
        composable(route = Screen.MainScreen.route){
            HomeScreen(onEvent = viewmodel::onEvent, state =state , navController = navController,viewmodel )
        }
        
        composable(route = Screen.ItemScreen.route){
            ItemScreen(sahaj = state.selectedItem, onEvent = viewmodel::onEvent,navController,viewmodel)
        }

        composable(
           route = Screen.SelectionScreen.route
        ){
            SelectionScreen(viewModel = viewmodel, navController = navController, onEvent = viewmodel::onEvent, state = state)
        }
        
    }
}

