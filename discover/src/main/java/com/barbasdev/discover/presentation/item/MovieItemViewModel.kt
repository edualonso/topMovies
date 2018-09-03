package com.barbasdev.discover.presentation.item

import com.barbasdev.discover.presentation.interfaces.DiscoverMovie

class MovieItemViewModel(
        private val discoverMovie: DiscoverMovie?
) {

    var title: String? = null
        get() = discoverMovie?.title ?: "PLACEHOLDER TITLE"

    var popularity: String? = null
        get() {
            return when (discoverMovie) {
                null -> "PLACEHOLDER POPULARITY"
                else -> discoverMovie.popularity.toString()
            }
        }
}
