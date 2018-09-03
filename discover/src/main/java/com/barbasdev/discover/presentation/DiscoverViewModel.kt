package com.barbasdev.discover.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.barbasdev.base.BaseViewModel
import com.barbasdev.base.livedata.Event
import com.barbasdev.discover.data.DiscoverRepository
import com.barbasdev.discover.presentation.interfaces.DiscoverMovie
import com.barbasdev.discover.presentation.interfaces.DiscoverResult
import io.reactivex.android.schedulers.AndroidSchedulers
import org.joda.time.DateTime
import java.util.*

/**
 *
 */
class DiscoverViewModel(
        val discoverInteractor: DiscoverInteractor
) : BaseViewModel() {

    private val _loadingVisible = MutableLiveData<Boolean>()
    val loadingVisible: LiveData<Boolean>
        get() = _loadingVisible

    private val _cacheExpired = MutableLiveData<Event<Boolean>>()
    val cacheExpired: LiveData<Event<Boolean>>
        get() = _cacheExpired

    private val _error = MutableLiveData<Event<Throwable>>()
    val error: LiveData<Event<Throwable>>
        get() = _error

    private var _updateTime = DateTime()
    val updateTime: DateTime
        get() = _updateTime

    lateinit var itemPagedList: LiveData<PagedList<DiscoverMovie>>


    init {
        initPagedList()
    }


    fun onResume() {
        // refresh cache
//        val now = System.currentTimeMillis()
//
//        Log.e("------->", "------- expiration:  ${DateTime(updateTime.millis + DiscoverRepository.CACHE_EXPIRES_AFTER)}")
//        Log.e("------->", "------- now:         ${DateTime(now)}")
//
//        if (updateTime.millis + DiscoverRepository.CACHE_EXPIRES_AFTER < now) {
//            clearCache()
//        }
    }

    fun initPagedList() {
        Log.e("------->", "------- initPagedList")

        val movieItemDataSourceFactory = MovieItemDataSourceFactory()
        val config = PagedList.Config.Builder()
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(INITIAL_LOAD_SIZE)
                .setPageSize(PAGE_SIZE)
                .build()
        itemPagedList = LivePagedListBuilder(movieItemDataSourceFactory, config)
                .build()
    }

    fun restoreCache(newUpdateTime: DateTime, cache: HashMap<Int, DiscoverResult>) {
        _updateTime = newUpdateTime
        discoverInteractor.restoreCache(cache)
    }

    fun clearCache() {
        Log.e("------->", "-------------- CACHE EXPIRED")
        _updateTime = DateTime()
        discoverInteractor.clearCache()
        _cacheExpired.value = Event(true)
    }


    companion object {
        const val INITIAL_LOAD_SIZE = 80
        const val PAGE_SIZE = 20
    }


    /**
     *
     */
    private inner class MovieItemDataSource : PageKeyedDataSource<Int, DiscoverMovie>() {

        override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, DiscoverMovie>) {
            _loadingVisible.postValue(true)

            discoverInteractor.getPopularMovies(1, updateTime)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        _loadingVisible. value = false

                        callback.onResult(it.results, 0, it.totalResults.toInt(), null, 2)
                    }, {
                        _error.value = Event(it)
                    })
                    .also {
                        disposables.add(it)
                    }
        }

        override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, DiscoverMovie>) {
            getPage(callback, params.key) {
                if (it.page < it.totalPages) {
                    params.key + 1
                } else {
                    null
                }
            }
        }

        override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, DiscoverMovie>) {
            getPage(callback, params.key) {
                if (params.key > 1) {
                    params.key - 1
                } else {
                    null
                }
            }
        }

        private fun getPage(
                callback: LoadCallback<Int, DiscoverMovie>,
                currentPage: Int,
                nextPage: ((DiscoverResult) -> Int?)?
        ) {
            discoverInteractor.getPopularMovies(currentPage, updateTime)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        callback.onResult(it.results, nextPage?.invoke(it))
                    }, {
                        _error.value = Event(it)
                    })
                    .also {
                        disposables.add(it)
                    }
        }
    }


    /**
     *
     */
    private inner class MovieItemDataSourceFactory : DataSource.Factory<Int, DiscoverMovie>() {

        override fun create(): DataSource<Int, DiscoverMovie> {
            return MovieItemDataSource()
        }
    }
}
