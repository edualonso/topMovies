package com.barbasdev.topmovies

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.barbasdev.discover.presentation.DiscoverFragment

/**
 * API Key (v3 auth)
 * 0de63959274df38be1139ab269854c44
 *
 * API Read Access Token (v4 auth)
 * eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIwZGU2Mzk1OTI3NGRmMzhiZTExMzlhYjI2OTg1NGM0NCIsInN1YiI6IjU4MzFjZWRhOTI1MTQxNjJjMDAyNzMzZSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.ueD6VBPxGWJ0XY6i8p1INjmA4snhkoflepuZ3bwWZrc
 *
 * Sample request
 * https://api.themoviedb.org/3/discover/movie?api_key=0de63959274df38be1139ab269854c44&language=en-US&sort_by=popularity.desc
 * https://api.themoviedb.org/3/discover/movie?api_key=0de63959274df38be1139ab269854c44&language=en-US&sort_by=popularity.desc&primary_release_year=2018
 *
 * More info (u: barbasdev - p: my usual password)
 * https://developers.themoviedb.org/3/discover/movie-discover
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val discoverFragment = supportFragmentManager.findFragmentByTag(DiscoverFragment::class.java.simpleName) ?: DiscoverFragment.newInstance()
        supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, discoverFragment, DiscoverFragment::class.java.simpleName)
                .commit()
    }
}
