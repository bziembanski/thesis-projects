package org.bziembanski.composeexample.screens.details_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun DetailsScreen(
    detailsViewModel: DetailsViewModel = viewModel(), personId: Int
) {
    val person = detailsViewModel.person.observeAsState()

    LaunchedEffect(key1 = Unit) { detailsViewModel.getPerson(personId) }

    if (person.value == null) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            CircularProgressIndicator()
        }
    } else {
        Column {
            InfoRow(key = "Name:", value = person.value!!.name)
            InfoRow(key = "Gender:", value = person.value!!.gender)
            InfoRow(key = "Height:", value = person.value!!.height)
            InfoRow(key = "Mass:", value = person.value!!.mass)
            InfoRow(key = "Skin color:", value = person.value!!.skinColor)
            InfoRow(key = "Hair color:", value = person.value!!.hairColor)
            InfoRow(key = "Eye color:", value = person.value!!.eyeColor)
            InfoRow(key = "Birth year:", value = person.value!!.birthYear)
        }
    }

}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun InfoRow(
    key: String, value: String
) {
    ListItem(
        modifier = Modifier.padding(vertical = 2.dp),
        text = { Text(text = key, fontSize = 16.sp, fontWeight = FontWeight.Bold) },
        trailing = { Text(text = value, fontSize = 16.sp) },
    )
    Divider()
}



