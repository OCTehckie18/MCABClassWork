package com.example.mcabclasswork.Labs.Lab5_Lab6

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.mcabclasswork.BuildConfig
import com.example.mcabclasswork.mainframe.Routes

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UnsplashApiService {
    @GET("photos")
    suspend fun getPhotos(
        @Query("client_id") apiKey: String,
        @Query("per_page") perPage: Int = 30
    ): List<UnsplashPhoto>

    @GET("search/photos")
    suspend fun searchPhotos(
        @Query("client_id") apiKey: String,
        @Query("query") query: String,
        @Query("per_page") perPage: Int = 30
    ): UnsplashSearchResponse

    @GET("photos/{id}")
    suspend fun getPhotoById(
        @Path("id") id: String,
        @Query("client_id") apiKey: String
    ): UnsplashPhoto
}

object RetrofitInstance {
    private const val BASE_URL = "https://api.unsplash.com/"

    val api: UnsplashApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UnsplashApiService::class.java)
    }
}

@Composable
fun Lab5_API(navController: NavController, sharedPhotoViewModel: SharedPhotoViewModel) {
    val viewModel: UnsplashViewModel = viewModel()
    val photos by viewModel.photos
    val isLoading by viewModel.isLoading
    val error by viewModel.error
    var query by remember { mutableStateOf("") }

    val apiKey = BuildConfig.UNSPLASH_KEY

    LaunchedEffect(Unit) {
        viewModel.fetchPhotos(apiKey)
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = query,
                onValueChange = { query = it },
                label = { Text("Search Photos") },
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = {
                if (query.isNotBlank()) {
                    viewModel.searchPhotos(apiKey, query)
                }
            }) {
                Text("Search")
            }
        }

        Box(modifier = Modifier.fillMaxSize()) {
            if (isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            } else if (error != null) {
                Text(
                    text = error ?: "Unknown Error",
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.align(Alignment.Center).padding(16.dp)
                )
            } else {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(8.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(photos) { photo ->
                        PhotoCard(
                            photo = photo,
                            onParcelClick = {
                                // Pass object via SavedStateHandle (most reliable for Parcelables in Compose Nav)
                                navController.currentBackStackEntry?.savedStateHandle?.set("photo", photo)
                                sharedPhotoViewModel.selectPhoto(photo) // Fallback
                                navController.navigate(Routes.Lab5_Detail)
                            },
                            onIdClick = {
                                // ID approach
                                navController.navigate("${Routes.Lab5_Detail_ByID}/${photo.id}")
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun PhotoCard(photo: UnsplashPhoto, onParcelClick: () -> Unit, onIdClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column {
            AsyncImage(
                model = photo.urls.small,
                contentDescription = photo.description,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                contentScale = ContentScale.Crop
            )
            photo.description?.let {
                Text(
                    text = it,
                    modifier = Modifier.padding(8.dp),
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 1
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth().padding(4.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(onClick = onParcelClick, modifier = Modifier.weight(1f), contentPadding = PaddingValues(4.dp)) {
                    Text("Parcel", style = MaterialTheme.typography.labelSmall)
                }
                Spacer(modifier = Modifier.width(4.dp))
                Button(onClick = onIdClick, modifier = Modifier.weight(1f), contentPadding = PaddingValues(4.dp)) {
                    Text("ID", style = MaterialTheme.typography.labelSmall)
                }
            }
        }
    }
}
