package com.example.jltodoapp

sealed class Screen(val route: String) {
    object HomeScreen : Screen("home_screen")
    object CallListScreen : Screen("call_list_screen")
    object ListingScreen : Screen("listing_screen")
}
