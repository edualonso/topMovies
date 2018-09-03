package com.barbasdev.discover.network

import com.barbasdev.discover.network.dto.DiscoverResultDto
import io.reactivex.Single
import org.joda.time.LocalDate
import retrofit2.http.GET
import retrofit2.http.Query

interface DiscoverService {

    @GET("discover/movie")
    fun getPopularMovies(
            @Query("sort_by") criterion: String,
            @Query("primary_release_year") primaryReleaseYear: Int? = LocalDate.now().year,
            @Query("page") page: Int? = null
    ): Single<DiscoverResultDto>

}
