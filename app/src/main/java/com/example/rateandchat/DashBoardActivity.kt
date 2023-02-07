package com.example.rateandchat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class DashBoardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash_board)


    }
    fun openMoviesActivity (){
        val intent = Intent(this, MoviesActivity::class.java)
        startActivity(intent)
    }
    fun openTvSeriesActivity (){
        val intent = Intent(this, TvSeriesActivity::class.java)
        startActivity(intent)
    }
    fun openTvProgramsActivity (){
        val intent = Intent(this, TvProgramsActivity::class.java)
        startActivity(intent)
    }
    fun openVideoGamesActivity (){
        val intent = Intent(this, VideoGamesActivity::class.java)
        startActivity(intent)
    }
    fun openSportsActivity (){
        val intent = Intent(this, SportsActivity::class.java)
        startActivity(intent)
    }
}