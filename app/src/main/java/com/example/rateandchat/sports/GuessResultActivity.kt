package com.example.rateandchat.sports

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.example.rateandchat.R

class GuessResultActivity : AppCompatActivity() {

    lateinit var teamATextView : TextView
    lateinit var teamBTextView : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guess_result)

        val teamA = intent.getStringExtra("teamAPlaying")!!
        val teamB = intent.getStringExtra("teamBPlaying")!!

        //displays which teams user can vote on

        teamATextView = findViewById(R.id.teamATextView)
        teamBTextView = findViewById(R.id.teamBTextView)
        teamATextView.text = teamA
        teamBTextView.text = teamB
    }
}