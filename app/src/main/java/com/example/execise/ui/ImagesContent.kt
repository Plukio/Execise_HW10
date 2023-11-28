package com.example.execise.ui

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.TextFieldColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.execise.DetailsActivity
import com.example.execise.R
import com.example.execise.UnsplashViewModel
import com.example.execise.data.model.UnsplashItem
import com.example.execise.utils.EXTRA_IMAGE

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ImagesContent(
  unsplashViewModel: UnsplashViewModel,
  onAction: (UnsplashItem) -> Unit
) {
  val unsplashImages = unsplashViewModel.items.observeAsState(emptyList())
  val loading = unsplashViewModel.loading.observeAsState(false)
  val pullRefreshState = rememberPullRefreshState(
    refreshing = loading.value,
    onRefresh = { unsplashViewModel.fetchImages() }
  )

  Box(
    Modifier.pullRefresh(pullRefreshState)
      .background(Color.Black)
  ) {

    LazyColumn (
      Modifier.background(Color.Black)
    ) {
      item {
        Spacer(modifier = Modifier.height(16.dp))
      }
      item {
        val search = remember { mutableStateOf("") }

        OutlinedTextField(
          value = search.value,
          onValueChange = { value ->
            search.value = value
          },
          modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
            .background(Color.Black)
          ,
          placeholder = {
            Text(
              text = stringResource(id = R.string.main_search_hint),
              color = Color.Gray,
              fontSize = 17.sp,
              fontWeight = FontWeight.Bold,
              maxLines = 1
            )
          },
          leadingIcon = {
            Icon(
              imageVector = Icons.Default.Search,
              contentDescription = stringResource(R.string.description_search)
            )
          },
          keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done
          ),
          keyboardActions = KeyboardActions {
            unsplashViewModel.searchImages(search.value)
          }
        )
      }
      items(unsplashImages.value) { image ->
        Card(
          modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .padding(8.dp)
            .clip(RoundedCornerShape(10.dp))
            .clickable { onAction(image) }
        ) {

          val painter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current)
              .data(image.urls.regular)
              .build()
          )

          Surface {

            Image(
              painter = painter,
              contentDescription = stringResource(id = R.string.description_image_preview),
              contentScale = ContentScale.Crop,
              modifier = Modifier.fillMaxSize()
            )

            Column(
              modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
              verticalArrangement = Arrangement.Bottom
            ) {
              Spacer(modifier = Modifier.height(4.dp))

              Button(
                onClick = { /* */},
                modifier = Modifier
                  .height(40.dp)
                  .wrapContentWidth()
                  .clip(RoundedCornerShape(25.dp)),
                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.black))
              ) {
                Text(text = "Photo by " + image.user.name,
                  fontSize = 17.sp,
                  fontWeight = FontWeight.Bold,
                  color = Color.White)
              }

            }
          }
        }
      }
    }

    PullRefreshIndicator(loading.value, pullRefreshState, Modifier.align(Alignment.TopCenter))
  }
}

