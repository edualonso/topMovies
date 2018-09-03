package com.barbasdev.discover.data.memory

import android.util.Log
import com.barbasdev.discover.data.DiscoverRepository
import com.barbasdev.discover.network.DiscoverApiClient
import com.barbasdev.discover.network.SortBy
import com.barbasdev.discover.presentation.interfaces.DiscoverResult
import io.reactivex.Single
import org.joda.time.DateTime

/**
 *
 */
class DiscoverMemoryRepository(
        private val discoverApiClient: DiscoverApiClient
): DiscoverRepository {

    var discoverResults = hashMapOf<Int, DiscoverResult>()

    override fun getPopularMovies(
            criterion: SortBy,
            page: Int,
            updateTime: DateTime
    ): Single<DiscoverResult> {
        return when {
            discoverResults.isEmpty() -> {
                Log.e("------->", "------- NO CACHE WHATSOEVER")
                getAndSave(criterion, page, updateTime)
            }
            else -> {
                discoverResults[page].let {
                    if (it == null) {
                        Log.e("------->", "------- PAGE $page MISSING FROM CACHE")
                        getAndSave(criterion, page, updateTime)
                    } else {
                        Log.e("------->", "------- PAGE $page RETURNED FROM CACHE")
                        Single.just(it)
                    }
                }
            }
        }
    }

    override fun getPopularMoviesCoroutine(
            criterion: SortBy,
            page: Int,
            updateTime: DateTime
    ): DiscoverResult {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun restoreCache(cache: HashMap<Int, DiscoverResult>) {
        discoverResults = cache
    }

    override fun getCache(): HashMap<Int, DiscoverResult> {
        return discoverResults
    }

    override fun clearCache() {
        discoverResults.clear()
    }

    private fun getAndSave(
            criterion: SortBy,
            page: Int,
            updateTime: DateTime
    ): Single<DiscoverResult> {
        return discoverApiClient.getPopularMovies(criterion, page)
                .map {
                    it.updateTime = updateTime.millis
                    discoverResults[page] = it
                    it as DiscoverResult
                }
    }
}















//package com.barbasdev.discover.data.memory
//
//import android.annotation.SuppressLint
//import android.util.Log
//import com.barbasdev.discover.network.DiscoverApiClient
//import com.barbasdev.discover.network.SortBy
//import com.barbasdev.discover.data.DiscoverRepository
//import com.barbasdev.discover.data.DiscoverRepository.Companion.CACHE_EXPIRES_AFTER
//import com.barbasdev.discover.presentation.interfaces.DiscoverResult
//import io.reactivex.Single
//
//class DiscoverMemoryRepository(
//        private val discoverApiClient: DiscoverApiClient
//): DiscoverRepository {
//
//    private var discoverResult: DiscoverResult? = null
//
//    override fun getPopularMovies(criterion: SortBy): Single<DiscoverResult> {
//        return when (discoverResult) {
//            null -> {
//                Log.e("------->", "------- NOTHING IN CACHE")
//                getAndSave(criterion)
//            }
//            else -> {
//                if (discoverResult!!.updateTime + CACHE_EXPIRES_AFTER < System.currentTimeMillis()) {
//                    Log.e("------->", "------- CACHE EXPIRED")
//                    getAndSave(criterion)
//                } else {
//                    Log.e("------->", "------- RETURNED FROM CACHE")
//                    Single.just(discoverResult!!)
//                }
//            }
//        }
//    }
//
//    override fun getPopularMoviesCoroutine(criterion: SortBy): DiscoverResult {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
//
//    @SuppressLint("CheckResult")
//    private fun getAndSave(criterion: SortBy): Single<DiscoverResult> {
//        return discoverApiClient.getPopularMovies(criterion)
//                .map {
//                    it.updateTime = System.currentTimeMillis()
//                    it as DiscoverResult
//                }
//                .doOnSuccess {
//                    discoverResult = it
//                }
//    }
//}
