package com.example.mymessage.ui

sealed class NavigationDestination(val destination: String) {
    data object AuthScreen: NavigationDestination("auth")
    data object RegisterScreen: NavigationDestination("register")
    data object UsersScreen: NavigationDestination("users")
    data object CocktailsScreen: NavigationDestination("cocktails")
    data object DetailsScreen: NavigationDestination("details/{cocktailId}")
}