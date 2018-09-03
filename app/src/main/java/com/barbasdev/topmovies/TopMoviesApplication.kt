package com.barbasdev.topmovies

import android.app.Application
import com.barbasdev.base.network.baseNetworkModule
import com.barbasdev.discover.di.discoverNetworkModule
import com.barbasdev.discover.di.discoverPersistenceModule
import com.barbasdev.discover.di.discoverPresentationModule
import org.koin.android.ext.android.startKoin
import org.koin.dsl.module.Module

class TopMoviesApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        val modules = listOf<Module>()
                .plus(baseNetworkModule)
                .plus(discoverNetworkModule)
                .plus(discoverPersistenceModule)
                .plus(discoverPresentationModule)
        startKoin(this, modules)
    }
}
