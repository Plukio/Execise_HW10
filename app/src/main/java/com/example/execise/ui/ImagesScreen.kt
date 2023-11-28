package com.example.execise.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.execise.Tab
import com.example.execise.UnsplashViewModel
import com.example.execise.data.model.UnsplashItem

@Composable
fun ImagesScreen(
  unsplashViewModel: UnsplashViewModel,
  onAction: (UnsplashItem) -> Unit
) {
  val selected = remember { mutableIntStateOf(0) }

  Column (modifier = Modifier.background(Color.Black)) {

    val actions = listOf(Tab.HOME, Tab.COLLECTIONS)
    TabRow(
      selectedTabIndex = selected.intValue,
      modifier = Modifier.height(48.dp)
        .background(Color.Black)
    ) {
      actions.forEachIndexed { index, _ ->
        Tab(
          selected = selected.intValue == index,
          onClick = { selected.intValue = index },
          modifier = Modifier.height(48.dp)
            .background(Color.Black),
        ) {
          Text(
            text = stringResource(id = Tab.entries[index].tab),
            color = Color.White
          )
        }
      }
    }

    when(selected.intValue) {
      Tab.HOME.ordinal -> {
        ImagesContent(
          unsplashViewModel = unsplashViewModel,
          onAction = { item -> onAction(item) }
        )
      }

      Tab.COLLECTIONS.ordinal -> {
        CollectionsContent(
          unsplashViewModel = unsplashViewModel
        )
      }
    }
  }
}