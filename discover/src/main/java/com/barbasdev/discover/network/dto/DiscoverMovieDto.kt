package com.barbasdev.discover.network.dto

import com.barbasdev.discover.data.room.movie.DiscoverMovieRoomImpl
import com.barbasdev.discover.presentation.interfaces.DiscoverMovie
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.Date

data class DiscoverMovieDto(
        override val id: Long,
        @SerializedName("vote_count") override val voteCount: Long,
        @SerializedName("imdb_id") override val imdbId: String? = null,
        override val video: Boolean,
        @SerializedName("vote_average") override val voteAverage: Float,
        override val title: String,
        override val popularity: Float,
        @SerializedName("poster_path") override val posterPath: String? = null,
        @SerializedName("original_language") override val originalLanguage: String,
        @SerializedName("original_title") override val originalTitle: String,
        @SerializedName("genre_ids") override val genreIds: List<Long> = listOf(),
        @SerializedName("backdrop_path") override val backdropPath: String? = null,
        override val adult: Boolean = false,
        override val overview: String? = null,
        @SerializedName("release_date") override val releaseDate: Date = Date()
) : DiscoverMovie, Serializable {

    fun toRoom(): DiscoverMovieRoomImpl {
        return DiscoverMovieRoomImpl(
                id,
                voteCount,
                imdbId,
                video,
                voteAverage,
                title,
                popularity,
                posterPath,
                originalLanguage,
                originalTitle,
                genreIds,
                backdropPath,
                adult,
                overview,
                releaseDate
        )
    }
}