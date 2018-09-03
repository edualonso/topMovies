package com.barbasdev.discover.di

import androidx.room.Room
import com.barbasdev.discover.data.DiscoverRepository
import com.barbasdev.discover.data.memory.DiscoverMemoryRepository
import com.barbasdev.discover.data.room.DiscoverRoomDatabase
import com.barbasdev.discover.data.room.DiscoverRoomRepository
import com.barbasdev.discover.network.DiscoverApiClient
import com.barbasdev.discover.network.DiscoverService
import com.barbasdev.discover.presentation.DiscoverInteractor
import com.barbasdev.discover.presentation.DiscoverViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit

/**
 *
 */
val discoverNetworkModule = module {

    single {
        get<Retrofit>().create(DiscoverService::class.java)
    }

    single {
        DiscoverApiClient(get())
    }
}


/**
 *
 */
const val DISCOVER_MEMORY_REPOSITORY = "DISCOVER_MEMORY_REPOSITORY"
const val DISCOVER_ROOM_REPOSITORY = "DISCOVER_ROOM_REPOSITORY"

val discoverPersistenceModule = module {

    single {
        Room.databaseBuilder(androidContext(), DiscoverRoomDatabase::class.java, "discover-db")
                .build()
    }

    single {
        get<DiscoverRoomDatabase>().getDiscoverResultDao()
    }

    single {
        get<DiscoverRoomDatabase>().getDiscoverMovieDao()
    }

    single(DISCOVER_MEMORY_REPOSITORY) {
        DiscoverMemoryRepository(get()) as DiscoverRepository
    }

    single(DISCOVER_ROOM_REPOSITORY) {
        DiscoverRoomRepository(get(), get(), get()) as DiscoverRepository
    }
}


/**
 *
 */
val discoverPresentationModule = module {

    single {
//        DiscoverInteractor(get(DISCOVER_MEMORY_REPOSITORY))
        DiscoverInteractor(get(DISCOVER_ROOM_REPOSITORY))
    }

    viewModel {
        DiscoverViewModel(get())
    }
}