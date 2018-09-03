package com.barbasdev.discover.network

import com.barbasdev.discover.network.dto.DiscoverMovieDto
import com.google.gson.Gson
import org.junit.Assert.assertEquals
import org.junit.Test
import java.text.SimpleDateFormat

class DiscoverMovieDtoTest {

    private val dateFormatter = SimpleDateFormat("yyyy-MM-dd")


    @Test
    fun canParseMovie() {
        with(Gson().fromJson(JSON.MOVIE, DiscoverMovieDto::class.java)) {
            assertEquals(18608, voteCount)
            assertEquals(27205, id)
            assertEquals(false, video)
            assertEquals(8.2f, voteAverage)
            assertEquals("Inception", title)
            assertEquals(30.161f, popularity)
            assertEquals("/qmDpIHrmpJINaRKAfWQfftjCdyi.jpg", posterPath)
            assertEquals("en", originalLanguage)
            assertEquals("Inception", originalTitle)
            with(genreIds) {
                assertEquals(5, size)
                assertEquals(get(0), 28)
                assertEquals(get(1), 53)
                assertEquals(get(2), 878)
                assertEquals(get(3), 9648)
                assertEquals(get(4), 12)
            }
            assertEquals("/s2bT29y0ngXxxu2IA8AOzzXTRhd.jpg", backdropPath)
            assertEquals(false, adult)
            assertEquals("Cobb, a skilled thief who commits corporate espionage by infiltrating the subconscious of his targets is offered a chance to regain his old life as payment for a task considered to be impossible: \"inception\", the implantation of another person's idea into a target's subconscious.", overview)
            assertEquals("2010-07-14", dateFormatter.format(releaseDate))
        }
    }


    object JSON {
        const val MOVIE =
                """
{
      "vote_count": 18608,
      "id": 27205,
      "video": false,
      "vote_average": 8.2,
      "title": "Inception",
      "popularity": 30.161,
      "poster_path": "/qmDpIHrmpJINaRKAfWQfftjCdyi.jpg",
      "original_language": "en",
      "original_title": "Inception",
      "genre_ids": [
        28,
        53,
        878,
        9648,
        12
      ],
      "backdrop_path": "/s2bT29y0ngXxxu2IA8AOzzXTRhd.jpg",
      "adult": false,
      "overview": "Cobb, a skilled thief who commits corporate espionage by infiltrating the subconscious of his targets is offered a chance to regain his old life as payment for a task considered to be impossible: \"inception\", the implantation of another person's idea into a target's subconscious.",
      "release_date": "2010-07-14"
    }
"""
    }
}