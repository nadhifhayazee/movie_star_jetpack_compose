package com.nadhif.hayazee.moviestar

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.nadhif.hayazee.moviestar.common.MOVIE_ID_KEY
import com.nadhif.hayazee.moviestar.common.MOVIE_TITLE_KEY
import com.nadhif.hayazee.moviestar.ui.navigation.NavigationItem
import com.nadhif.hayazee.moviestar.ui.navigation.Screen
import com.nadhif.hayazee.moviestar.ui.screen.about.AboutScreen
import com.nadhif.hayazee.moviestar.ui.screen.movie.MovieScreen
import com.nadhif.hayazee.moviestar.ui.screen.movie.ShowAllMovieScreen
import com.nadhif.hayazee.moviestar.ui.screen.movie_detail.MovieDetailScreen
import com.nadhif.hayazee.moviestar.ui.screen.search.SearchScreen
import com.nadhif.hayazee.moviestar.ui.screen.watchlist.WatchlistScreen
import com.nadhif.hayazee.moviestar.ui.theme.MovieStarTheme

@Composable
fun MovieStarApp(
    modifier: Modifier = Modifier, navController: NavHostController = rememberNavController()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    Scaffold(
        bottomBar = {
            if (currentRoute != Screen.MovieDetail.route && currentRoute != Screen.AllMovie.route
                && currentRoute != Screen.Search.route
            ) BottomBar(
                navController = navController
            )
        }, modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Movie.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Movie.route) {
                MovieScreen(onNavigateToMovieDetail = { movieId ->
                    navController.navigate(Screen.MovieDetail.createRoute(movieId))

                }, onNavigateToAllMovie = { title ->
                    navController.navigate(Screen.AllMovie.createRoute(title))
                }, onNavigateToSearch = {
                    navController.navigate(Screen.Search.route)
                })
            }
            composable(Screen.Watchlist.route) {
                WatchlistScreen(navigateToMovieDetail = { movieId ->
                    navController.navigate(Screen.MovieDetail.createRoute(movieId))
                }, navigateToSearch = {
                    navController.navigate(Screen.Search.route)
                })
            }
            composable(Screen.About.route) {
                AboutScreen(context = LocalContext.current)
            }

            composable(
                route = Screen.MovieDetail.route,
                arguments = listOf(navArgument(MOVIE_ID_KEY) { type = NavType.IntType })
            ) {
                val id = it.arguments?.getInt(MOVIE_ID_KEY) ?: -1
                MovieDetailScreen(movieId = id, navigateBack = {
                    navController.navigateUp()
                }, context = LocalContext.current)
            }

            composable(
                route = Screen.AllMovie.route, arguments = listOf(navArgument(MOVIE_TITLE_KEY) {
                    type = NavType.StringType
                })
            ) {
                val title = it.arguments?.getString(MOVIE_TITLE_KEY) ?: ""
                ShowAllMovieScreen(title = title, navigateToMovieDetail = { movieId ->
                    navController.navigate(Screen.MovieDetail.createRoute(movieId))
                }, onBackPress = {
                    navController.navigateUp()
                })
            }

            composable(
                route = Screen.Search.route
            ) {
                SearchScreen(onBackPress = {
                    navController.navigateUp()
                }, onItemClicked = {
                    navController.navigate(Screen.MovieDetail.createRoute(it))
                })
            }
        }
    }

}

@Composable
fun BottomBar(
    navController: NavHostController, modifier: Modifier = Modifier
) {

    val navigationItems = listOf(
        NavigationItem(
            title = stringResource(id = R.string.menu_movie),
            icon = Icons.Default.Home,
            screen = Screen.Movie
        ),
        NavigationItem(
            title = stringResource(id = R.string.menu_watchlist),
            icon = Icons.Default.Favorite,
            screen = Screen.Watchlist
        ),
        NavigationItem(
            title = stringResource(id = R.string.menu_about),
            icon = Icons.Default.Info,
            screen = Screen.About
        ),
    )

    BottomNavigation(
        modifier = modifier
    ) {


        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        navigationItems.map { item ->
            BottomNavigationItem(icon = {
                Icon(imageVector = item.icon, contentDescription = item.title)
            }, label = {
                Text(text = item.title)
            }, selected = currentRoute == item.screen.route, onClick = {
                navController.navigate(item.screen.route) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    restoreState = true
                    launchSingleTop = true
                }
            })
        }
    }
}

@Preview(
    showBackground = true,
)
@Composable
fun DefaultPreview() {
    MovieStarTheme {
        MovieStarApp()
    }
}