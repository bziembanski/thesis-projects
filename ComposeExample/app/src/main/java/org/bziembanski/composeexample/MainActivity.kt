package org.bziembanski.composeexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.bziembanski.composeexample.screens.FirstScreen
import org.bziembanski.composeexample.screens.SecondScreen
import org.bziembanski.composeexample.theme.MyApplicationTheme

class MainActivity: ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      val navController = rememberNavController()
      MyApplicationTheme {
        Scaffold(
          topBar = {
            TopAppBar(
              title = {
                Text(
                  text = "Jetpack Compose Example",
                )
              },
              backgroundColor = MaterialTheme.colors.primary,
            )
          },
          bottomBar = {
            BottomNavigation {
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
            composable(route = "firstScreen") { FirstScreen() }
            composable(route = "secondScreen") { SecondScreen() }
          }
        }
      }
    }
  }
}
