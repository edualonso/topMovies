package com.barbasdev.discover.presentation.interfaces

import java.util.*

/**
 *
 */
interface DiscoverMovie {
    val voteCount: Long
    val id: Long
    val imdbId: String?
    val video: Boolean
    val voteAverage: Float
    val title: String
    val popularity: Float
    val posterPath: String?
    val originalLanguage: String
    val originalTitle: String
    val genreIds: List<Long>
    val backdropPath: String?
    val adult: Boolean
    val overview: String?
    val releaseDate: Date
}
