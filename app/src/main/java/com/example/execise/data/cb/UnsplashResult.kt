package com.example.execise.data.cb


import com.example.execise.data.model.ImageX
import com.example.execise.data.model.UnsplashCollection
import com.example.execise.data.model.UnsplashItem

interface UnsplashResult {

  fun onDataFetchedSuccess(images: List<UnsplashItem>)

  fun onCollectionsFetchedSuccess(collections: List<UnsplashCollection>)

  fun onDataFetchedFailed()

  fun onFindImageSuccess(image: ImageX)
}