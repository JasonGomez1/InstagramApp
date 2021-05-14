package com.example.instagramapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import androidx.navigation.compose.*
import com.example.instagramapp.data.remote.Endpoints
import com.example.instagramapp.ui.home.HomeViewModel
import com.example.instagramapp.ui.theme.InstagramAppTheme
import com.example.instagramapp.utils.CLIENT_ID
import com.example.instagramapp.utils.REDIRECT_URI
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var homeViewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = Screen.Login.route) {
                composable(route = Screen.Login.route) {
                    LoginScreen()
                }
            }
        }
    }
}

sealed class Screen(open val route: String) {
    object Login : Screen("login")
    object Authorization : Screen("authorization")
    object Home : Screen("home")

    sealed class BottomNavScreen(
        override val route: String,
        @StringRes val resourceId: Int,
        @DrawableRes val drawableId: Int
    ) : Screen(route) {
        object Posts : BottomNavScreen("home", R.string.home, R.drawable.ic_home)
        object Videos : BottomNavScreen("videos", R.string.videos, R.drawable.ic_videocam)
        object Albums : BottomNavScreen("albums", R.string.Albums, R.drawable.ic_photo_album)
    }
}

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun AuthorizationScreen() {
    InstagramAppTheme {
        AndroidView(modifier = Modifier.fillMaxSize(), factory = { context ->
            WebView(context).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                webViewClient = WebViewClient()
                settings.javaScriptEnabled = true
                loadUrl("${Endpoints.AUTHORIZE}?client_id=$CLIENT_ID&redirect_uri=$REDIRECT_URI&scope=user_profile,user_media&response_type=code")
            }
        })
    }
}

@Composable
fun HomeScreen(navController: NavController) {
    InstagramAppTheme {
        Scaffold(bottomBar = {
            BottomNavigation {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.arguments?.getString(KEY_ROUTE)
                listOf(
                    Screen.BottomNavScreen.Posts,
                    Screen.BottomNavScreen.Videos,
                    Screen.BottomNavScreen.Albums
                ).forEach { screen ->
                    BottomNavigationItem(
                        icon = { Icon(painterResource(id = screen.drawableId), null) },
                        label = { Text(stringResource(screen.resourceId)) },
                        selected = currentRoute == screen.route,
                        onClick = {
                        }
                    )
                }
            }
        }
        ) {

        }
    }
}

@Composable
fun LoginScreen() {
    InstagramAppTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            OutlinedTextField(
                value = "",
                onValueChange = { },
                label = { Text("Username") }
            )
            OutlinedTextField(
                value = "",
                onValueChange = { },
                label = { Text("Password") }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    InstagramAppTheme {
        HomeScreen(navController = rememberNavController())
    }
}