package com.example.rateandchat.sports

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.TextView
import com.example.rateandchat.BasicActivity
import com.example.rateandchat.R
import com.example.rateandchat.main.MainActivity
import com.example.rateandchat.profile.MyPageActivity
import com.example.rateandchat.profile.UserListActivity
import com.google.firebase.auth.FirebaseAuth

class GuessResultActivity : BasicActivity() {



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