package com.example.execise.data.model

data class UnsplashSearch(
  val results: List<UnsplashItem>,
  val total: Int,
  val total_pages: Int
)