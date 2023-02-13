package com.example.rateandchat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class SportsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sports)

        //gömmer Action Bar längst upp
        supportActionBar?.hide()
    }

    fun openGuessSeasonActivity (view: View){
        val intent = Intent(this, SeasonGuessActivity::class.java)
        startActivity(intent)
    }
}