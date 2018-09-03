package com.barbasdev.discover.data.room.result

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Maybe
import io.reactivex.Single

@Dao
interface DiscoverResultDao {

//    @Query("SELECT * FROM DiscoverResultRoomImpl")
//    fun getAll(): Single<DiscoverResultRoomImpl>
//
//    @Query("SELECT * FROM DiscoverResultRoomImpl WHERE page = :page")
//    fun get(page: Int): Single<DiscoverResultRoomImpl>

//    @Query("SELECT * FROM DiscoverResultRoomImpl WHERE page = :page")
//    fun get(page: Int): Single<DiscoverResultRoomImpl>

    @Query("SELECT * FROM DiscoverResultRoomImpl WHERE page = :page")
    fun get(page: Int): DiscoverResultRoomImpl?

    @Insert
    fun insert(discoverResultRoomImpl: DiscoverResultRoomImpl)

    @Query("DELETE FROM DiscoverResultRoomImpl")
    fun delete()
}