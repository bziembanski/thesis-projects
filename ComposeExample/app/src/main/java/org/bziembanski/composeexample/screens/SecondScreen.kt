package org.bziembanski.composeexample.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.bziembanski.composeexample.R

@Composable
fun SecondScreen() {
    Column(Modifier.verticalScroll(rememberScrollState()).fillMaxSize()) {
        Box(
            Modifier
                .padding(top = 32.dp)
                .fillMaxWidth()
                .padding(horizontal = 128.dp)
                .shadow(elevation = 16.dp, clip = true, shape = CircleShape),
            contentAlignment = Alignment.Center,
        ) {
            Image(painter = painterResource(id = R.drawable.avatar), contentDescription = "avatar")
        }
        Box(
            Modifier
                .padding(top = 24.dp)
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .shadow(elevation = 16.dp, shape = RoundedCornerShape(32.dp))
                .background(MaterialTheme.colors.primarySurface)
                .padding(16.dp)

        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Text(text = "Name", fontSize = 16.sp, color = MaterialTheme.colors.onSurface)
                Text(text = "Surname", fontSize = 16.sp, color = MaterialTheme.colors.onSurface)
            }
        }

        Box(
            Modifier
                .padding(top = 24.dp)
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .shadow(elevation = 16.dp, shape = RoundedCornerShape(24.dp))
                .background(MaterialTheme.colors.primarySurface)
                .padding(24.dp)
        ) {
            Text(
                text = stringResource(R.string.description),
                fontSize = 16.sp,
                lineHeight = 24.sp,
                fontStyle = FontStyle.Italic,
                color = MaterialTheme.colors.onSurface,
                textAlign = TextAlign.Justify
            )
        }

        Row(
            modifier = Modifier
                .padding(top = 32.dp, bottom = 32.dp)
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Icon(
                modifier = Modifier.size(48.dp),
                painter = painterResource(id = R.drawable.baseline_account_circle_24),
                contentDescription = "account",
                tint = MaterialTheme.colors.primary,
            )
            Icon(
                modifier = Modifier.size(48.dp),
                painter = painterResource(id = R.drawable.baseline_calendar_month_24),
                contentDescription = "account",
                tint = MaterialTheme.colors.primary,
            )
            Icon(
                modifier = Modifier.size(48.dp),
                painter = painterResource(id = R.drawable.baseline_camera_24),
                contentDescription = "account",
                tint = MaterialTheme.colors.primary,
            )
        }
    }
}
