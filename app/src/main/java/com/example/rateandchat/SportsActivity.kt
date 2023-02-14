package com.example.rateandchat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class SportsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sports)
    }

    //skapa bara för att prova gå vidare till gissning
    fun testToGuessActivity (view : View){
        val intent = Intent(this, GuessResultActivity::class.java)
        startActivity(intent)
    }
}