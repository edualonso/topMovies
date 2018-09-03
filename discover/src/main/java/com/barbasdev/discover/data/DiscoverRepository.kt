package com.barbasdev.discover.data

import com.barbasdev.discover.network.SortBy
import com.barbasdev.discover.presentation.interfaces.DiscoverResult
import io.reactivex.Single
import org.joda.time.DateTime

/**
 *
 */
interface DiscoverRepository {

    fun getPopularMovies(
            criterion: SortBy,
            page: Int,
            updateTime: DateTime
    ): Single<DiscoverResult>

    fun getPopularMoviesCoroutine(
            criterion: SortBy,
            page: Int,
            updateTime: DateTime
    ): DiscoverResult

    fun restoreCache(cache: HashMap<Int, DiscoverResult>)
    fun getCache(): HashMap<Int, DiscoverResult>
    fun clearCache()

    companion object {
        const val CACHE_EXPIRES_AFTER = 20000
//        const val CACHE_EXPIRES_AFTER = 24 * 3600 * 1000
    }
}
