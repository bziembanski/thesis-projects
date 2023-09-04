package org.bziembanski.composeexample.screens.first_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ListItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FirstScreen(
    firstViewModel: FirstViewModel = viewModel(),
    navController: NavController
) {
    val people = firstViewModel.people.observeAsState()

    LaunchedEffect(key1 = Unit) {
        firstViewModel.getPeople()
    }

    if (people.value.isNullOrEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            CircularProgressIndicator()
        }
    } else {
        LazyColumn {
            items(people.value!!.size) { it ->
                val person = people.value!![it]
                val id = person.url.split("/").last { it.isNotEmpty() }.toInt()
                ListItem(
                    text = { Text(text = person.name) },
                    secondaryText = { Text(text = person.gender) },
                    modifier = Modifier
                        .clickable {
                            navController.navigate("detailsScreen/${id}")
                        }
                )
                Divider()
            }
        }
    }

}



