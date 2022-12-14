package com.nadhif.hayazee.moviestar.ui.navigation

sealed class Screen(val route: String) {
    object Movie : Screen("movie")
    object Watchlist : Screen("watchlist")
    object About : Screen("about")
    object MovieDetail: Screen("movie/{movieId}"){
        fun createRoute(movieId: Int) = "movie/$movieId"
    }
    object AllMovie: Screen("movie/{title}"){
        fun createRoute(title: String) = "movie/$title"
    }
    object Search: Screen("search")
}
