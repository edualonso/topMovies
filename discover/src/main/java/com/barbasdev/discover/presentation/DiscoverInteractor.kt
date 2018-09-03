package com.barbasdev.discover.presentation

import com.barbasdev.discover.data.DiscoverRepository
import com.barbasdev.discover.network.SortBy
import com.barbasdev.discover.presentation.interfaces.DiscoverResult
import io.reactivex.Single
import org.joda.time.DateTime

/**
 *
 */
class DiscoverInteractor(
        val discoverRepository: DiscoverRepository
) {

//    fun getPopularMoviesState(page: Int? = null): Observable<DiscoverState> {
//        return discoverApiClient.getPopularMovies(SortBy.PopularityDesc(), page)
//                .toObservable()
//                .map<DiscoverState> {
//                    DiscoverState.Ready(it)
//                }
//                .startWith(DiscoverState.Loading())
//                .onErrorReturn {
//                    DiscoverState.Error(it)
//                }
//    }

    fun getPopularMovies(page: Int, updateTime: DateTime): Single<DiscoverResult> {
        return discoverRepository.getPopularMovies(SortBy.PopularityDesc(), page, updateTime)
    }

    fun clearCache() {
        discoverRepository.clearCache()
    }

    fun restoreCache(cache: HashMap<Int, DiscoverResult>) {
        discoverRepository.restoreCache(cache)
    }
}


/**
 *
 */
sealed class DiscoverState {
    class Loading                                       : DiscoverState()
    class Ready(val discoverResult: DiscoverResult)     : DiscoverState()
    class Error(val throwable: Throwable)               : DiscoverState()
}














//package com.barbasdev.discover.presentation
//
//import com.barbasdev.discover.network.DiscoverApiClient
//import com.barbasdev.discover.network.SortBy
//import com.barbasdev.discover.presentation.interfaces.DiscoverResult
//import io.reactivex.Observable
//import io.reactivex.Single
//
///**
// *
// */
//class DiscoverInteractor(
//        private val discoverApiClient: DiscoverApiClient
//) {
//
//    fun getPopularMoviesState(page: Int? = null): Observable<DiscoverState> {
//        return discoverApiClient.getPopularMovies(SortBy.PopularityDesc(), page)
//                .toObservable()
//                .map<DiscoverState> {
//                    DiscoverState.Ready(it)
//                }
//                .startWith(DiscoverState.Loading())
//                .onErrorReturn {
//                    DiscoverState.Error(it)
//                }
//    }
//
//    fun getPopularMovies(page: Int? = null): Single<DiscoverResult> {
//        return discoverApiClient.getPopularMovies(SortBy.PopularityDesc(), page)
//                .map {
//                    it
//                }
//    }
//
//}
//
//
///**
// *
// */
//sealed class DiscoverState {
//    class Loading                                       : DiscoverState()
//    class Ready(val discoverResult: DiscoverResult)     : DiscoverState()
//    class Error(val throwable: Throwable)               : DiscoverState()
//}
