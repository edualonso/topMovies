package com.barbasdev.discover.data.room.movie

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.barbasdev.discover.network.dto.DiscoverMovieDto
import com.barbasdev.discover.presentation.interfaces.DiscoverMovie
import java.util.*

@Entity
data class DiscoverMovieRoomImpl(
        @PrimaryKey override val id: Long,
        override val voteCount: Long,
        override val imdbId: String? = null,
        override val video: Boolean,
        override val voteAverage: Float,
        override val title: String,
        override val popularity: Float,
        override val posterPath: String? = null,
        override val originalLanguage: String,
        override val originalTitle: String,
        override val genreIds: List<Long> = listOf(),
        override val backdropPath: String? = null,
        override val adult: Boolean = false,
        override val overview: String? = null,
        override val releaseDate: Date = Date()
) : DiscoverMovie {

    var resultsPage: Int = 0

    fun toDto(): DiscoverMovieDto {
        return DiscoverMovieDto(
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
