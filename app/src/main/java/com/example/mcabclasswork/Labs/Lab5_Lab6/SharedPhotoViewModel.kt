package com.example.mcabclasswork.Labs.Lab5_Lab6

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class SharedPhotoViewModel : ViewModel() {
    private val _selectedPhoto = mutableStateOf<UnsplashPhoto?>(null)
    val selectedPhoto: State<UnsplashPhoto?> = _selectedPhoto

    fun selectPhoto(photo: UnsplashPhoto) {
        _selectedPhoto.value = photo
    }
}
