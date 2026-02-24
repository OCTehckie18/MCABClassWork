package com.example.mcabclasswork.Labs.Lab4_ViewModel

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Lab4_Counter(counterViewModel: CounterViewModel) {
    //val count = remember { mutableStateOf(0) }
    val count = counterViewModel.count.value


//    fun increment () {
//        count.value++
//    }
//
//    fun decrement() {
//        count.value--
//    }


    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Count: ${count}")
        Spacer(modifier = Modifier.height(16.dp))
        Row (
            modifier = Modifier
                .height(50.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = {
                counterViewModel.increment()
            }) {
                Text(text = "Increment")
            }
            Button(onClick = {
                counterViewModel.decrement()
            }) {
                Text(text = "Decrement")
            }
        }


    }
}