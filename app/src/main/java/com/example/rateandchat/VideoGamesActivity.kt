package com.example.rateandchat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class VideoGamesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_games)

        //gömmer Action Bar längst upp
        supportActionBar?.hide()
    }
}