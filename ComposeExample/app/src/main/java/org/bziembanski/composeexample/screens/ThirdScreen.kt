package org.bziembanski.composeexample.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.bziembanski.composeexample.R

private val colorRange = (0..255)
private val radiusRange = (0..128)

@Composable
fun ThirdScreen() {
  val listState = rememberLazyListState()
  LaunchedEffect("scroll") {
    listState.apply {
      for ( i in 1..10){
        animateScrollToItem((i*100)-1)
      }
    }
  }

  LazyColumn(state = listState) {
    items(1000, key = { it }) {
      Box(
        Modifier
          .fillMaxWidth()
          .padding(16.dp)
          .shadow(
            elevation = 16.dp,
            clip = true,
            shape = RoundedCornerShape(
              radiusRange
                .random()
                .toFloat().dp
            )
          ),
        contentAlignment = Alignment.Center,
      ) {
        Image(
          painter = painterResource(id = R.drawable.image),
          contentDescription = "image",
          colorFilter = ColorFilter.tint(
            Color(
              colorRange.random(),
              colorRange.random(),
              colorRange.random(),
            ),
            blendMode = BlendMode.Screen
          )
        )
      }
    }
  }
}
