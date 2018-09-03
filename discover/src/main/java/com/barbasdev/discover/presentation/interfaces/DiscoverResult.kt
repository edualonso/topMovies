package com.barbasdev.discover.presentation.interfaces

/**
 *
 */
interface DiscoverResult {
    val page: Long
    val totalResults: Long
    val totalPages: Long
    val results: List<DiscoverMovie>
    var updateTime: Long
}
