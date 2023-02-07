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
    fun openMoviesActivity (view: View){
        val intent = Intent(this, MoviesActivity::class.java)
        startActivity(intent)
    }
    fun openTvSeriesActivity (view: View){
        val intent = Intent(this, TvSeriesActivity::class.java)
        startActivity(intent)
    }
    fun openTvProgramsActivity (view: View){
        val intent = Intent(this, TvProgramsActivity::class.java)
        startActivity(intent)
    }
    fun openVideoGamesActivity (view: View){
        val intent = Intent(this, VideoGamesActivity::class.java)
        startActivity(intent)
    }
    fun openSportsActivity (view: View){
        val intent = Intent(this, SportsActivity::class.java)
        startActivity(intent)
    }
}