package com.example.mcabclasswork.Labs

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mcabclasswork.R
import kotlin.random.Random

@Composable
fun Lab0() {

    var direction by remember { mutableStateOf("") }
    var count by remember { mutableStateOf(0) }
    var trueDirection by remember { mutableStateOf("") }
    val treasure = R.drawable.treasure
    val storm = R.drawable.seastorm
    var curImg by remember { mutableStateOf(R.drawable.ship) }
    val randomValue = Random.nextBoolean()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center

    ){
        // image
        Image(
            painter = painterResource(id = curImg),
            contentDescription = "Button background image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // text
        Column(
            modifier = Modifier
                .background(
                    color = Color.White.copy(alpha = 0.75f),
                    shape = RoundedCornerShape(20.dp)
                )
                .padding(24.dp),   // space inside the box
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Text(
                text = "Current Direction: $direction",
                fontSize = 30.sp,
                color = Color.Blue
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Treasure Found: $count",
                fontSize = 30.sp,
                color = Color.Blue

            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                trueDirection,
                fontSize = 30.sp,
                color = Color.Blue

            )

            Spacer(modifier = Modifier.height(20.dp))

            val directionsView = listOf(
                listOf("North","East"),
                listOf("West","South")
            )

            directionsView.forEach { dir->
                Row(
                    modifier = Modifier.padding(10.dp)
                ) {
                    dir.forEach { rowItem ->
                        Button(
                            onClick = {
                                direction = rowItem
                                if (randomValue){
                                    trueDirection = "We Found a treasure"
                                    count+=1
                                    curImg = treasure
                                } else{
                                    trueDirection = "Storm Ahead"
                                    curImg = storm

                                }
                            },
                            modifier = Modifier.padding(10.dp)
                        ) {
                            Text(text=rowItem,fontSize = 20.sp)
                        }
                    }
                }
            }
        }


    }

}