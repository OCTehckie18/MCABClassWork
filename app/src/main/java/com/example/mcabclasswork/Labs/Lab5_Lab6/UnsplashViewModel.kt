package com.example.mcabclasswork.Labs.Lab5_Lab6

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class UnsplashViewModel : ViewModel() {
    private val _photos = mutableStateOf<List<UnsplashPhoto>>(emptyList())
    val photos: State<List<UnsplashPhoto>> = _photos

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _error = mutableStateOf<String?>(null)
    val error: State<String?> = _error

    private val _selectedPhoto = mutableStateOf<UnsplashPhoto?>(null)
    val selectedPhoto: State<UnsplashPhoto?> = _selectedPhoto

    fun fetchPhotos(apiKey: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val response = RetrofitInstance.api.getPhotos(apiKey)
                _photos.value = response
            } catch (e: Exception) {
                _error.value = "Failed to fetch photos: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun searchPhotos(apiKey: String, query: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val response = RetrofitInstance.api.searchPhotos(apiKey, query)
                _photos.value = response.results
            } catch (e: Exception) {
                _error.value = "Failed to search photos: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun fetchPhotoById(apiKey: String, photoId: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val photo = RetrofitInstance.api.getPhotoById(photoId, apiKey)
                _selectedPhoto.value = photo
            } catch (e: Exception) {
                _error.value = "Failed to fetch photo details: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}
