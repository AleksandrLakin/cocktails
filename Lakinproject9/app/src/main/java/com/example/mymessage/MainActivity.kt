package com.example.mymessage

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.cocktails.presentation.details.DetailsView
import com.example.cocktails.presentation.main.CocktailsView
import com.example.mymessage.app.App
import com.example.mymessage.ui.NavigationDestination
import com.example.mymessage.ui.auth.AuthScreen
import com.example.mymessage.ui.register.RegisterScreen
import com.example.mymessage.ui.theme.MyMessageTheme
import com.example.mymessage.ui.users.UsersScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            MyMessageTheme {
                val navController = rememberNavController()
                val bottomBarState = rememberSaveable { (mutableStateOf(false)) }
                val navBackStackEntry by navController.currentBackStackEntryAsState()

                when (navBackStackEntry?.destination?.route) {
                    NavigationDestination.AuthScreen.destination -> {
                        bottomBarState.value = false
                    }

                    NavigationDestination.RegisterScreen.destination -> {
                        bottomBarState.value = false
                    }

                    else -> {
                        bottomBarState.value = true
                    }
                }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = { BottomAppBar(navController, bottomBarState) }
                ) { innerPadding ->

                    NavHost(navController = navController, startDestination = NavigationDestination.AuthScreen.destination) {

                        composable(NavigationDestination.AuthScreen.destination) {
                            val viewModel = App.appComponent.getAuthComponent().viewModel()
                            AuthScreen(modifier = Modifier.padding(innerPadding), navController = navController, viewModel = viewModel)
                        }

                        composable(NavigationDestination.RegisterScreen.destination) {
                            val viewModel = App.appComponent.getRegisterComponent().viewModel()
                            RegisterScreen(modifier = Modifier.padding(innerPadding), navController = navController, viewModel = viewModel)
                        }

                        composable(NavigationDestination.UsersScreen.destination) {
                            val viewModel = App.appComponent.getUsersComponent().viewModel()
                            UsersScreen(modifier = Modifier.padding(innerPadding), navController = navController, viewModel = viewModel)
                        }

                        composable(NavigationDestination.CocktailsScreen.destination) {
                            val viewModel = App.appComponent.getCocktailComponent().viewModel()
                            CocktailsView(modifier = Modifier.padding(innerPadding), navController = navController, viewModel = viewModel)
                        }

                        composable(NavigationDestination.DetailsScreen.destination) {
                            val viewModel = App.appComponent.getDetailsComponent().viewModel()
                            DetailsView(
                                modifier = Modifier.padding(innerPadding),
                                navController = navController,
                                viewModel = viewModel,
                                cocktailId = it.arguments?.getString("cocktailId") ?: ""
                            )
                        }
                    }
                }
            }
        }
    }

}

@Composable
fun BottomAppBar(
    navController: NavController,
    bottomBarState: MutableState<Boolean>
) {
    //val navController = rememberNavController()
    AnimatedVisibility(
        visible = bottomBarState.value,
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it }),
        content = {
            BottomNavigation {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                val items = listOf(
                    NavigationDestination.UsersScreen,
                    NavigationDestination.CocktailsScreen,
                )
                items.forEach { screen ->
                    BottomNavigationItem(
                        icon = { Icon(Icons.Filled.Favorite, contentDescription = null) },
                        label = { Text(screen.destination) },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.destination } == true,
                        onClick = {
                            navController.navigate(screen.destination) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    )
}