package com.example.execise.data.model

data class UnsplashCollectionItem(
    val cover_photo: CoverPhoto,
    val description: String,
    val id: Int,
    val last_collected_at: String,
    val links: Links,
    val `private`: Boolean,
    val published_at: String,
    val share_key: String,
    val title: String,
    val total_photos: Int,
    val updated_at: String,
    val user: User
)