package com.example.execise.ui

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.execise.R

sealed class BottomNavigationScreen(
  val route: String,
  @StringRes val title: Int,
  val icon: ImageVector
) {

  data object Home: BottomNavigationScreen(
    "Home",
    R.string.main_tab_home,
    Icons.Default.Home
  )

  data object About: BottomNavigationScreen(
    "About",
    R.string.main_tab_about,
    Icons.Default.Info
  )

}