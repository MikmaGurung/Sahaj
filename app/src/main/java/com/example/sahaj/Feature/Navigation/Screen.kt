package com.example.sahaj.Feature.Navigation

sealed class Screen (val route : String) {
    object MainScreen : Screen("main_screen")
    object ItemScreen : Screen("item_screen")
    object SelectionScreen : Screen("Selection_screen")
}