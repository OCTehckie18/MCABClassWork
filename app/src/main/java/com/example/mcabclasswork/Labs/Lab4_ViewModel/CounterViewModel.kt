package com.example.mcabclasswork.Labs.Lab4_ViewModel

import androidx.compose.runtime.mutableStateOf


import androidx.lifecycle.ViewModel


class CounterViewModel: ViewModel() {
    private val _count = mutableStateOf(0)
    val count = _count


    fun increment() {
        _count.value++
    }


    fun decrement() {
        _count.value--
        if (_count.value <  0)
            _count.value = 0

    }
}
