package com.barbasdev.discover.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.barbasdev.base.room.Converters
import com.barbasdev.discover.data.room.movie.DiscoverMovieDao
import com.barbasdev.discover.data.room.movie.DiscoverMovieRoomImpl
import com.barbasdev.discover.data.room.result.DiscoverResultDao
import com.barbasdev.discover.data.room.result.DiscoverResultRoomImpl

@Database(
        entities = [
            DiscoverResultRoomImpl::class,
            DiscoverMovieRoomImpl::class
        ],
        version = 1
)
@TypeConverters(Converters::class)
abstract class DiscoverRoomDatabase : RoomDatabase() {
    abstract fun getDiscoverResultDao(): DiscoverResultDao
    abstract fun getDiscoverMovieDao(): DiscoverMovieDao
}