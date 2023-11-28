package com.example.execise.data.model

data class ImageX(
    val blur_hash: String,
    val color: String,
    val created_at: String,
    val description: String,
    val downloads: Int,
    val height: Int,
    val id: String,
    val liked_by_user: Boolean,
    val likes: Int,
    val public_domain: Boolean,
    val updated_at: String,
    val width: Int,
    val exif: Exif
)