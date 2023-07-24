package org.bziembanski.composeexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import org.bziembanski.composeexample.screens.first_screen.FirstScreen
import org.bziembanski.composeexample.screens.SecondScreen
import org.bziembanski.composeexample.screens.details_screen.DetailsScreen
import org.bziembanski.composeexample.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            var canPop by remember {
                mutableStateOf(false)
            }
            var route by remember {
                mutableStateOf("firstScreen")
            }

            DisposableEffect(navController) {
                val listener =
                    NavController.OnDestinationChangedListener { controller, destination, _ ->
                        canPop = controller.previousBackStackEntry != null
                        route = destination.route ?: ""
                    }
                navController.addOnDestinationChangedListener(listener)
                onDispose {
                    navController.removeOnDestinationChangedListener(listener)
                }
            }
            MyApplicationTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(
                                    text = if (!route.contains("detailsScreen")) "Jetpack Compose Example" else "Details",
                                )
                            },
                            backgroundColor = MaterialTheme.colors.primary,
                            navigationIcon = if (canPop) {
                                {
                                    IconButton(onClick = { navController.popBackStack() }) {
                                        Icon(
                                            imageVector = Icons.Filled.ArrowBack,
                                            contentDescription = null
                                        )
                                    }
                                }
                            } else {
                                null
                            }
                        )
                    },
                    bottomBar = {
                        if (route == "firstScreen" || route == "secondScreen") BottomNavigation {
                            val navBackStackEntry by navController.currentBackStackEntryAsState()
                            val currentDestination = navBackStackEntry?.destination
                            listOf(
                                Pair("firstScreen", "First Screen"),
                                Pair("secondScreen", "Second Screen")
                            ).forEach {
                                val selected = currentDestination?.route == it.first
                                BottomNavigationItem(
                                    icon = {
                                        Icon(
                                            painterResource(
                                                if (it.first == "firstScreen") R.drawable.first_screen
                                                else R.drawable.second_screen,
                                            ),
                                            contentDescription = null,
                                        )
                                    },
                                    label = {
                                        Text(
                                            text = it.second,
                                        )
                                    },
                                    selected = selected,
                                    onClick = {
                                        navController.navigate(it.first) {
                                            popUpTo(navController.graph.findStartDestination().id) {
                                                saveState = true
                                            }
                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                    },
                                )
                            }
                        }
                    },
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "firstScreen",
                        Modifier.padding(innerPadding)
                    ) {
                        composable(route = "firstScreen") { FirstScreen(navController = navController) }
                        composable(route = "secondScreen") { SecondScreen() }
                        composable(
                            route = "detailsScreen/{personId}",
                            arguments = listOf(navArgument("personId") { type = NavType.IntType })
                        ) {
                            DetailsScreen(personId = it.arguments!!.getInt("personId"))
                        }
                    }
                }
            }
        }
    }
}
