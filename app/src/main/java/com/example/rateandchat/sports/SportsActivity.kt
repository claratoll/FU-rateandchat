package com.example.rateandchat.sports

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.rateandchat.GuessResultActivity
import com.example.rateandchat.R

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

    //skapa bara för att prova gå vidare till gissning
    fun testToGuessActivity (view : View){
        val intent = Intent(this, GuessResultActivity::class.java)
        startActivity(intent)
    }
}