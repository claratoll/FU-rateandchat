package com.example.rateandchat.sports

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import com.example.rateandchat.BasicActivity
import com.example.rateandchat.R
import com.example.rateandchat.main.MainActivity
import com.example.rateandchat.profile.MyPageActivity
import com.example.rateandchat.profile.UserListActivity
import com.google.firebase.auth.FirebaseAuth

class GuessResultActivity : BasicActivity() {



    lateinit var teamATextView : TextView
    lateinit var teamBTextView : TextView
    lateinit var choiceCheckBox1 : CheckBox
    lateinit var choiceCheckBox2 : CheckBox
    lateinit var choiceCheckBox3 : CheckBox

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

    fun voteResult(view: View){
        choiceCheckBox1 = findViewById(R.id.choiceCheckBox1)
        choiceCheckBox2 = findViewById(R.id.choiceCheckBox2)
        choiceCheckBox3 = findViewById(R.id.choiceCheckBox3)

        if(choiceCheckBox1.isChecked && choiceCheckBox2.isChecked && choiceCheckBox3.isChecked
            || choiceCheckBox1.isChecked && choiceCheckBox2.isChecked
            || choiceCheckBox1.isChecked && choiceCheckBox3.isChecked
            || choiceCheckBox2.isChecked && choiceCheckBox3.isChecked){
            Toast.makeText(this,"Choose only one choice!!",Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this,"Thank you for your vote!!", Toast.LENGTH_SHORT).show()
            finish()
        }


    }
}