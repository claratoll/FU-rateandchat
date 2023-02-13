package com.example.rateandchat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MoviesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)

        //gömmer Action Bar längst upp
        supportActionBar?.hide()
    }
}