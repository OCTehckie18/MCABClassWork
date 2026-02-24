package com.example.mcabclasswork.Labs.Lab5

data class UnsplashPhoto(
    val id: String,
    val description: String?,
    val urls: UnsplashUrls
)

data class UnsplashUrls(
    val raw: String,
    val full: String,
    val regular: String,
    val small: String,
    val thumb: String
)

data class UnsplashSearchResponse(
    val results: List<UnsplashPhoto>
)
