package com.barbasdev.discover.network.dto

import com.barbasdev.discover.data.room.result.DiscoverResultRoomImpl
import com.barbasdev.discover.presentation.interfaces.DiscoverResult
import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 *
 */
data class DiscoverResultDto(
        override val page: Long,
        @SerializedName("total_results") override val totalResults: Long,
        @SerializedName("total_pages") override val totalPages: Long,
        override val results: List<DiscoverMovieDto>
) : DiscoverResult, Serializable {

    override var updateTime: Long = 666     // not used here


    fun toRoom(): DiscoverResultRoomImpl {
        return DiscoverResultRoomImpl(
                page, totalResults, totalPages
        )
    }
}
