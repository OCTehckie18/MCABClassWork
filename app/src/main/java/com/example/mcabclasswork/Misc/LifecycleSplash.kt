package com.example.mcabclasswork.Misc

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mcabclasswork.R
import com.example.mcabclasswork.mainframe.Routes
import kotlinx.coroutines.delay

@Composable
fun LifecycleSplash(navController: NavController) {

    LaunchedEffect(Unit) {
        delay(2000)
        navController.navigate(Routes.LifecycleDemo) {
            popUpTo(Routes.LifecycleSplash) { inclusive = true }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                modifier = Modifier.size(140.dp),
                strokeWidth = 4.dp
            )

            Image(
                painter = painterResource(R.drawable.profilepic),
                contentDescription = null,
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
            )
        }

        Spacer(modifier = Modifier.height(12.dp))
        Text("Omkaar Chakraborty")
        Spacer(modifier = Modifier.height(12.dp))
        Text("Register No: 2547237")
    }
}

