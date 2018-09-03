package com.barbasdev.discover.data.room

import android.annotation.SuppressLint
import android.util.Log
import com.barbasdev.discover.data.DiscoverRepository
import com.barbasdev.discover.data.room.movie.DiscoverMovieDao
import com.barbasdev.discover.data.room.movie.DiscoverMovieRoomImpl
import com.barbasdev.discover.data.room.result.DiscoverResultDao
import com.barbasdev.discover.data.room.result.DiscoverResultRoomImpl
import com.barbasdev.discover.network.DiscoverApiClient
import com.barbasdev.discover.network.SortBy
import com.barbasdev.discover.network.dto.DiscoverResultDto
import com.barbasdev.discover.presentation.interfaces.DiscoverResult
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.joda.time.DateTime
import org.reactivestreams.Subscriber

/**
 *
 */
class DiscoverRoomRepository(
        private val discoverApiClient: DiscoverApiClient,
        private val discoverResultDao: DiscoverResultDao,
        private val discoverMovieDao: DiscoverMovieDao
) : DiscoverRepository {

    override fun getPopularMovies(
            criterion: SortBy,
            page: Int,
            updateTime: DateTime
    ): Single<DiscoverResult> {
        return Single.fromPublisher<DiscoverResult> { emitter ->
            val resultRoomImpl: DiscoverResultRoomImpl? = discoverResultDao.get(page)
            if (resultRoomImpl != null) {
                // result found - fetch movies belonging to this page from Room
                Log.e("------->", "------- PAGE $page RETURNED FROM CACHE")

                getFromRoom(criterion, page, resultRoomImpl, emitter)
            } else {
                // result not found - fetch page from the API
                Log.e("------->", "------- PAGE $page MISSING FROM CACHE")

                getFromApi(criterion, page, updateTime, emitter)
            }
        }.subscribeOn(Schedulers.io())
    }

    override fun getPopularMoviesCoroutine(
            criterion: SortBy,
            page: Int,
            updateTime: DateTime
    ): DiscoverResult {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun restoreCache(cache: HashMap<Int, DiscoverResult>) {
        // do nothing
    }

    override fun getCache(): HashMap<Int, DiscoverResult> {
        // not used, just return an empty hashmap
        return hashMapOf()
    }

    override fun clearCache() {
        // do nothing
    }


    @SuppressLint("CheckResult")
    private fun getFromApi(
            criterion: SortBy,
            page: Int,
            updateTime: DateTime,
            emitter: Subscriber<in DiscoverResult>
    ) {
        discoverApiClient.getPopularMovies(criterion, page)
                .map {
                    // convert result to room object and store it
                    val discoverResultRoomImpl = it.toRoom().apply {
                        this.updateTime = updateTime.millis
                    }
                    discoverResultDao.insert(discoverResultRoomImpl)

                    // extract movies and store them too
                    val movies = it.results.map { movieDto ->
                        movieDto.toRoom().apply {
                            resultsPage = page
                        }
                    }
                    discoverMovieDao.insert(movies)

//                    movies.forEach { movie ->
//                        try {
//                            discoverMovieDao.insert(movie)
//                        } catch (throwable: Throwable) {
//                            val existingIdMovie = discoverMovieDao.getById(movie.id).first()
//                            throw RuntimeException("(1) Error while storing movie ${movie.title} with id ${movie.id}. " +
//                                    "There is another movie (${existingIdMovie.title}) with the same id (${existingIdMovie.id}): ${throwable.message}")
//                        }
//                    }

                    // return the original DTO
                    it
                }
                .subscribe({
                    // publish the result after having stored everything
                    emitter.onNext(it)
                    emitter.onComplete()
                }, {
                    throw RuntimeException("(2) Error while storing results and movies: ${it.message}")
                })
    }

    private fun getFromRoom(
            criterion: SortBy,
            page: Int,
            resultRoomImpl: DiscoverResultRoomImpl,
            emitter: Subscriber<in DiscoverResult>
    ) {
        val moviesRoomImpl: List<DiscoverMovieRoomImpl> = discoverMovieDao.getPopular(page)

        if (moviesRoomImpl.isNotEmpty()) {
            val result = DiscoverResultDto(
                    resultRoomImpl.page,
                    resultRoomImpl.totalResults,
                    resultRoomImpl.totalPages,
                    moviesRoomImpl.map {
                        it.toDto()
                    })

            emitter.onNext(result)
            emitter.onComplete()
        } else {
            throw AssertionError("Cannot have results without movies.")
        }
    }
}











//package com.barbasdev.discover.persistence.room
//
//import android.util.Log
//import androidx.room.EmptyResultSetException
//import com.barbasdev.discover.network.DiscoverApiClient
//import com.barbasdev.discover.network.SortBy
//import com.barbasdev.discover.data.DiscoverRepository
//import com.barbasdev.discover.data.DiscoverRepository.Companion.CACHE_EXPIRES_AFTER
//import com.barbasdev.discover.data.DiscoverMovieDao
//import com.barbasdev.discover.data.DiscoverMovieRoomImpl
//import com.barbasdev.discover.data.DiscoverResultDao
//import com.barbasdev.discover.data.DiscoverResultRoomImpl
//import com.barbasdev.discover.presentation.interfaces.DiscoverResult
//import io.reactivex.Single
//import io.reactivex.schedulers.Schedulers
//
///**
// *
// */
//class DiscoverRoomRepository(
//        private val discoverApiClient: DiscoverApiClient,
//        private val discoverResultDao: DiscoverResultDao,
//        private val discoverMovieDao: DiscoverMovieDao
//) : DiscoverRepository {
//
//    override fun getPopularMovies(criterion: SortBy): Single<DiscoverResult> {
//        return discoverResultDao.getAll()
//                .subscribeOn(Schedulers.io())
//                .onErrorResumeNext {
//                    if (it is EmptyResultSetException) {
//                        Log.e("------->", "------- NOTHING IN DB")
//                        dropAndSave(criterion)
//                    } else {
//                        throw it
//                    }
//                }
//                .flatMap {
//                    if (it.updateTime + CACHE_EXPIRES_AFTER < System.currentTimeMillis()) {
//                        Log.e("------->", "------- DB EXPIRED")
//                        dropAndSave(criterion)
//                    } else {
//                        Log.e("------->", "------- RETURNED FROM DB")
//
//                        discoverMovieDao.get(it.page)
//                                .flatMap { movies ->
//                                    it.results = movies
//                                    Single.just(it)
//                                }
//                    }
//                }
//    }
//
//    override fun getPopularMoviesCoroutine(criterion: SortBy): DiscoverResult {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
//
//
//    private fun dropAndSave(criterion: SortBy): Single<DiscoverResultRoomImpl> {
//        return discoverApiClient.getPopularMovies(criterion)
//                .flatMap { discoverResult ->
//                    val discoverResultRoomImpl = DiscoverResultRoomImpl(
//                            discoverResult.page,
//                            discoverResult.totalResults,
//                            discoverResult.totalPages
//                    ).apply {
//                        results.forEach {
//                            val discoverMovieRoomImpl = DiscoverMovieRoomImpl(
//                                    it.id, it.voteCount, it.imdbId,
//                                    it.video, it.voteAverage, it.title,
//                                    it.popularity, it.posterPath, it.originalLanguage,
//                                    it.originalTitle, it.genreIds, it.backdropPath,
//                                    it.adult, it.overview, it.releaseDate
//                            ).apply {
//                                resultsPage = page
//                            }
//                            discoverMovieDao.insert(discoverMovieRoomImpl)
//                        }
//
//                        updateTime = System.currentTimeMillis()
//
//                        discoverResultDao.delete()
//                        discoverResultDao.insert(this)
//                    }
//                    Single.just(discoverResultRoomImpl)
//                }
//    }
//}