package com.barbasdev.discover.data.room.movie

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DiscoverMovieDao {

    @Query("SELECT * FROM DiscoverMovieRoomImpl WHERE id = :id ORDER BY popularity DESC")
    fun getById(id: Long): List<DiscoverMovieRoomImpl>

    @Query("SELECT * FROM DiscoverMovieRoomImpl ORDER BY popularity DESC")
    fun getAll(): List<DiscoverMovieRoomImpl>

    @Query("SELECT * FROM DiscoverMovieRoomImpl WHERE resultsPage = :page ORDER BY popularity DESC")
    fun getPopular(page: Int): List<DiscoverMovieRoomImpl>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movie: DiscoverMovieRoomImpl)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movies: List<DiscoverMovieRoomImpl>)

    @Query("DELETE FROM DiscoverMovieRoomImpl")
    fun delete()
}