package com.example.mcabclasswork.Labs.Lab5_Lab6

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UnsplashPhoto(
    val id: String,
    val description: String?,
    val urls: UnsplashUrls,
    val user: UnsplashUser?,
    val location: UnsplashLocation?
) : Parcelable

@Parcelize
data class UnsplashUrls(
    val raw: String,
    val full: String,
    val regular: String,
    val small: String,
    val thumb: String
) : Parcelable

@Parcelize
data class UnsplashUser(
    val name: String,
    val username: String
) : Parcelable

@Parcelize
data class UnsplashLocation(
    val name: String?,
    val city: String?,
    val country: String?
) : Parcelable

data class UnsplashSearchResponse(
    val results: List<UnsplashPhoto>
)
