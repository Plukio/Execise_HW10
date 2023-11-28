package com.example.execise

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.execise.data.model.UnsplashItem
import com.example.execise.ui.AboutContent
import com.example.execise.ui.BottomNavigationScreen
import com.example.execise.ui.ImagesScreen
import com.example.execise.ui.theme.ExeciseTheme
import com.example.execise.utils.EXTRA_IMAGE
import com.example.execise.utils.EXTRA_IMAGE_ID

enum class Tab(@StringRes val tab: Int) {
    HOME(R.string.main_tab_images),
    COLLECTIONS(R.string.main_tab_collections)
}

class MainActivity : ComponentActivity() {

    private val unsplashViewModel: UnsplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        unsplashViewModel.fetchImages()
        unsplashViewModel.fetchCollections()

        setContent {
            ExeciseTheme {
                val navController = rememberNavController()

                Scaffold(
                    bottomBar = {
                        val items = listOf(
                            BottomNavigationScreen.Home,
                            BottomNavigationScreen.About
                        )

                        val selected = remember { mutableIntStateOf(0) }

                        NavigationBar {
                            items.forEachIndexed { index, bottomNavigationScreen ->
                                NavigationBarItem(
                                    selected = selected.intValue == index,
                                    onClick = {
                                        selected.intValue = index
                                        navController.navigate(bottomNavigationScreen.route)
                                    },
                                    icon = {
                                        Icon(
                                            imageVector = bottomNavigationScreen.icon,
                                            contentDescription = stringResource(id = bottomNavigationScreen.title)
                                        )
                                    },
                                    label = {
                                        Text(text = stringResource(bottomNavigationScreen.title))
                                    }
                                )
                            }
                        }
                    },

                    content = {
                        Column(modifier = Modifier.padding(it)) {
                            NavHost(
                                navController = navController,
                                startDestination = BottomNavigationScreen.Home.route,
                                builder = {
                                    composable(BottomNavigationScreen.Home.route) {
                                        ImagesScreen(
                                            unsplashViewModel = unsplashViewModel,
                                            onAction = { image -> openDetails(image) }
                                        )
                                    }

                                    composable(BottomNavigationScreen.About.route) {
                                        AboutContent()
                                    }
                                }
                            )
                        }
                    }
                )
            }
        }
    }

    private fun openDetails(image: UnsplashItem) {
        val intent = Intent(this@MainActivity, DetailsActivity::class.java)
        intent.putExtra(EXTRA_IMAGE, image.urls.regular)
        intent.putExtra(EXTRA_IMAGE_ID, image.id)
        startActivity(intent)
    }
}