package com.barbasdev.discover.network

import com.barbasdev.discover.network.dto.DiscoverResultDto
import com.barbasdev.discover.presentation.interfaces.DiscoverResult
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

/**
 *
 */
class DiscoverApiClient(
        private val discoverService: DiscoverService
) {

    /**
     *
     */
    fun getPopularMovies(
            criterion: SortBy,
            page: Int? = null
    ): Single<DiscoverResultDto> {
        return discoverService.getPopularMovies(criterion.criterionApiValue, page = page)
                .subscribeOn(Schedulers.io())
                .map {
                    it
                }
    }

    /**
     *
     */
    fun getPopularMoviesCoroutine(criterion: SortBy): DiscoverResult {
        TODO()
    }
}


/**
 *
 */
sealed class SortBy(
        val criterionApiValue: String
) {
    class PopularityAsc             : SortBy("popularity.asc")
    class PopularityDesc            : SortBy("popularity.desc")
    class ReleaseDateAsc            : SortBy("release_date.asc")
    class ReleaseDateDesc           : SortBy("release_date.desc")
    class RevenueAsc                : SortBy("revenue.asc")
    class RevenueDesc               : SortBy("revenue.desc")
    class PrimaryReleaseDateAsc     : SortBy("primary_release_date.asc")
    class PrimaryReleaseDateDesc    : SortBy("primary_release_date.desc")
    class OriginalTitleAsc          : SortBy("original_title.asc")
    class OriginalTitleDesc         : SortBy("original_title.desc")
    class VoteAverageAsc            : SortBy("vote_average.asc")
    class VoteAverageDesc           : SortBy("vote_average.desc")
    class VoteCountAsc              : SortBy("vote_count.asc")
    class VoteCountDesc             : SortBy("vote_count.desc")
}
