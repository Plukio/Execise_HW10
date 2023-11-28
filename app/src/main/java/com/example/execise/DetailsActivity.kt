package com.example.execise

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.execise.data.model.ImageX
import com.example.execise.ui.theme.ExeciseTheme
import com.example.execise.utils.EXTRA_IMAGE
import com.example.execise.utils.EXTRA_IMAGE_ID

class DetailsActivity : ComponentActivity() {

  private val unsplashViewModel: UnsplashViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val url = if (intent.hasExtra(EXTRA_IMAGE)) {
      intent.extras?.get(EXTRA_IMAGE)
    } else {
      ""
    }

    val id = if (intent.hasExtra(EXTRA_IMAGE_ID)) {
      intent.extras?.get(EXTRA_IMAGE_ID)
    } else {
      ""
    }

    unsplashViewModel.findImage(id.toString())

    setContent {
      ExeciseTheme {

        val theImage = unsplashViewModel.item.observeAsState()

        Surface(
          modifier = Modifier.fillMaxSize()
            .background(Color.Black)
        ) {
          LazyColumn (
            modifier = Modifier.background(Color.Black)
          ){
            item {

              val painter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(LocalContext.current)
                  .data(url)
                  .build()
              )

              Box(modifier = Modifier
                .height(300.dp)
                .fillMaxWidth()
              ) {
                Image(
                  painter = painter,
                  contentDescription = null,
                  modifier = Modifier.fillMaxSize(),
                  contentScale = ContentScale.Crop
                )
                Box(modifier = Modifier
                  .wrapContentWidth()
                  .wrapContentHeight()
                  .padding(18.dp)
                  .align(Alignment.BottomStart),
                ){

                  Location()

                }

              }
            }

            item{
              Profile()
            }

            item {
              Divider(
                modifier = Modifier.padding(start = 16.dp, end = 16.dp),
                thickness = 1.dp,
                color = Color.LightGray
              )
            }

            item {
              Row(
                modifier = Modifier.padding(16.dp)
              ) {
                Column(
                  modifier = Modifier.weight(1.0f)
                ) {
                  AddImageInformation(
                    title = stringResource(id = R.string.details_camera_title),
                    subtitle = theImage.value?.exif?.name?: "-")
                }

                Column(
                  modifier = Modifier.weight(1.0f)
                ) {
                  AddImageInformation(
                    title = stringResource(id = R.string.details_aperture_title),
                    subtitle = theImage.value?.exif?.aperture?: "-",
                  )
                }
              }
            }

            item {
              Row(
                modifier = Modifier.padding(start = 16.dp, end = 16.dp)
              ) {
                Column(
                  modifier = Modifier.weight(1.0f)
                ) {
                  AddImageInformation(
                    title = stringResource(id = R.string.details_focal_title),
                    subtitle = theImage.value?.exif?.focalLength?: "-"
                  )
                }

                Column(
                  modifier = Modifier.weight(1.0f)
                ) {
                  AddImageInformation(
                    title = stringResource(id = R.string.details_shutter_title),
                    subtitle = theImage.value?.exif?.exposureTime?: "-"
                  )
                }
              }
            }

            item {
              Row(
                modifier = Modifier.padding(16.dp)
              ) {
                Column(
                  modifier = Modifier.weight(1.0f)
                ) {
                  AddImageInformation(
                    title = stringResource(id = R.string.details_iso_title),
                    subtitle = (theImage.value?.exif?.iso?: "-").toString()
                  )
                }

                Column(
                  modifier = Modifier.weight(1.0f)
                ) {
                  AddImageInformation(
                    title = stringResource(id = R.string.details_dimensions_title),
                    subtitle = (theImage.value?.height?: "-").toString() + "x" + (theImage.value?.width?: "-").toString()
                  )
                }
              }
            }

            item {
              Divider(
                modifier = Modifier.padding(start = 16.dp, end = 16.dp),
                thickness = 1.dp,
                color = Color.LightGray
              )
            }

            item {
              Row(
                modifier = Modifier
                  .fillMaxWidth()
                  .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
              ) {

                Column(
                  horizontalAlignment = Alignment.CenterHorizontally
                ) {
                  AddImageInformation(
                    title = stringResource(id = R.string.details_views_title),
                    subtitle = "-"
                  )
                }

                Column(
                  horizontalAlignment = Alignment.CenterHorizontally
                ) {
                  AddImageInformation(
                    title = stringResource(id = R.string.details_downloads_title),
                    subtitle = (theImage.value?.downloads?: "-").toString()
                  )
                }

                Column(
                  horizontalAlignment = Alignment.CenterHorizontally
                ) {
                  AddImageInformation(
                    title = stringResource(id = R.string.details_likes_title),
                    subtitle = (theImage.value?.likes?: "-").toString(),

                  )
                }
              }
            }
            item {
              Divider(
                modifier = Modifier.padding(start = 16.dp, end = 16.dp),
                thickness = 1.dp,
                color = Color.LightGray
              )
            }
            item { ButtonsSection() }
          }
        }
      }
    }
  }
}

@Composable
fun AddImageInformation(
  title: String,
  subtitle: String
) {

  Text(
    text = title,
    fontSize = 15.sp,
    fontWeight = FontWeight.Bold,
    color = Color.Gray
  )

  Text(
    text = subtitle,
    fontSize = 17.sp,
    color = Color.White
  )
}

@Composable
@Preview
fun Profile() {
    LazyRow(
      modifier = Modifier.fillMaxWidth()
        .padding(12.dp),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      item {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Image(
              painter = painterResource(id = R.drawable.ic_launcher_background),
              contentDescription = null,
              modifier = Modifier
                .size(40.dp)
                .clip(CircleShape),
              contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(12.dp))

            Text(text = "Android", color = Color.White, fontSize = 20.sp)
          }
        }
      }

      item { Icons() }
    }

}

@Composable
fun Icons() {
  Row(
    modifier = Modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically
  ) {

    IconButton(onClick = { /*TODO*/ }) {
      Image(
        painter = painterResource(id = R.drawable.upload),
        contentDescription = null,
        modifier = Modifier
          .size(20.dp),
        contentScale = ContentScale.Inside
      )
    }


    Spacer(modifier = Modifier.width(12.dp))

    IconButton(onClick = { /*TODO*/ }) {
      Image(
        painter = painterResource(id = R.drawable.like),
        contentDescription = null,
        modifier = Modifier
          .size(20.dp),
        contentScale = ContentScale.Inside
      )
    }

    Spacer(modifier = Modifier.width(12.dp))

    IconButton(onClick = { /*TODO*/ }) {
      Image(
        painter = painterResource(id = R.drawable.bookmark),
        contentDescription = null,
        modifier = Modifier
          .size(20.dp),
        contentScale = ContentScale.Inside
      )
    }
  }
}
@Composable
fun ButtonsSection() {
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .padding(18.dp),
    horizontalArrangement = Arrangement.Start,
    verticalAlignment = Alignment.CenterVertically
  ) {
    Button(
      onClick = { /* */},
      modifier = Modifier
        .height(40.dp)
        .wrapContentWidth()
        .clip(RoundedCornerShape(25.dp)),
      colors = ButtonDefaults.buttonColors(colorResource(id = R.color.tranparence))
    ) {
      Text(text = "Bangkok", color = Color.White, fontSize = 14.sp)
    }

    Spacer(modifier = Modifier.width(12.dp))

    Button(
      onClick = { /* */},
      modifier = Modifier
        .height(40.dp)
        .wrapContentWidth()
        .clip(RoundedCornerShape(25.dp)),
      colors = ButtonDefaults.buttonColors(colorResource(id = R.color.tranparence))
    ) {
      Text(text = "Thailand", color = Color.White, fontSize = 14.sp)
    }
  }
}

@Composable
fun Location() {
  Row(
    modifier = Modifier.wrapContentWidth(),
    horizontalArrangement = Arrangement.Start,
    verticalAlignment = Alignment.CenterVertically
  ) {
    Image(
      painter = painterResource(id = R.drawable.pin),
      contentDescription = null,
      modifier = Modifier
        .size(30.dp),
      contentScale = ContentScale.Inside
    )

    Spacer(modifier = Modifier.width(12.dp))

    Text(text = "Bangkok", color = Color.White, fontSize = 20.sp)
  }
}