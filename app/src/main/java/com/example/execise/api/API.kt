package com.example.execise.api

import com.example.execise.data.model.ImageX
import com.example.execise.data.model.UnsplashCollection
import com.example.execise.data.model.UnsplashItem
import com.example.execise.data.model.UnsplashSearch
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.PATCH
import retrofit2.http.Path
import retrofit2.http.Query

private const val AUTHORIZATION_CLIENT_ID = "Client-ID"
private const val ACCESS_KEY = "AiG7G53EwDJYnNjdr_6BB8oP_CEwl-GTmIMznbY8yZs"

interface UnsplashApi {

    @Headers("Authorization: $AUTHORIZATION_CLIENT_ID $ACCESS_KEY")
    @GET("photos")
    fun fetchPhotos() : Call<List<UnsplashItem>>

    @Headers("Authorization: $AUTHORIZATION_CLIENT_ID $ACCESS_KEY")
    @GET("photos/{id}")
    fun findImage(@Path(value = "id") id : String): Call<ImageX>

    @Headers("Authorization: $AUTHORIZATION_CLIENT_ID $ACCESS_KEY")
    @GET("search/photos")
    fun searchPhotos(@Query(value = "query") keyword : String): Call<UnsplashSearch>

    @Headers("Authorization: $AUTHORIZATION_CLIENT_ID $ACCESS_KEY")
    @GET("collections")
    fun fetchCollections() : Call<List<UnsplashCollection>>
}