package com.example.mcabclasswork.Labs.Lab4_ViewModel


import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.ViewModel


class CounterViewModel: ViewModel() {
    private val _count = mutableIntStateOf(0)
    val count = _count


    fun increment() {
        _count.intValue++
    }


    fun decrement() {
        _count.intValue--
        if (_count.intValue <  0)
            _count.intValue = 0

    }
}
