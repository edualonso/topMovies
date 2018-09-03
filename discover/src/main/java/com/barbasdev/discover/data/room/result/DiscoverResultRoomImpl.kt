package com.barbasdev.discover.data.room.result

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.barbasdev.discover.presentation.interfaces.DiscoverMovie
import com.barbasdev.discover.presentation.interfaces.DiscoverResult

@Entity
data class DiscoverResultRoomImpl(
        @PrimaryKey override var page: Long,
        override var totalResults: Long,
        override var totalPages: Long
) : DiscoverResult {

    override var updateTime: Long = System.currentTimeMillis()
    @Ignore override var results: List<DiscoverMovie> = listOf()
}
