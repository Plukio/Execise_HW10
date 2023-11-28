package com.example.execise


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.execise.data.model.UnsplashCollection
import com.example.execise.data.model.UnsplashItem
import com.example.execise.data.cb.UnsplashResult
import com.example.execise.api.ApiProvider
import com.example.execise.data.model.ImageX


private const val TAG = "UnsplashViewModel"
class UnsplashViewModel : ViewModel(), UnsplashResult {

    private val _items = MutableLiveData<List<UnsplashItem>>()
    val items: LiveData<List<UnsplashItem>> = _items

    private val _item = MutableLiveData<ImageX>()
    val item: LiveData<ImageX> = _item

    private val _collections = MutableLiveData<List<UnsplashCollection>>()
    val collections: LiveData<List<UnsplashCollection>> = _collections

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    private val provider by lazy {
        ApiProvider()
    }

    fun searchImages(keyword: String) {
        provider.searchImages(keyword, this)
    }

    fun fetchImages() {
        _loading.value = true
        provider.fetchImages(this)
    }

    fun fetchCollections() {
        provider.fetchCollections(this)
    }

    fun findImage(id: String) {
        provider.findImage(id,this)
    }

    override fun onDataFetchedSuccess(images: List<UnsplashItem>) {
        Log.d(TAG, "onDataFetchedSuccess | Received ${images.size} images")
        _items.value = images
        _loading.value = false
    }

    override fun onCollectionsFetchedSuccess(collections: List<UnsplashCollection>) {
        Log.d(TAG, "onCollectionsFetchedSuccess | Received ${collections.size} collections")
        _collections.value = collections
    }

    override fun onDataFetchedFailed() {
        Log.e(TAG, "onDataFetchedFailed | Unable to retrieve images")
    }


    override fun onFindImageSuccess(image: ImageX) {
        Log.d(TAG, "onFindImageSuccess | Received ${image.id}")
        _item.value =  image
        _loading.value = false
    }
}