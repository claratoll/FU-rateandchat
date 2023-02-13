package com.example.rateandchat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.rateandchat.R

class SeasonGuessActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_season_guess)

        //gömmer Action Bar längst upp
        supportActionBar?.hide()
    }
}