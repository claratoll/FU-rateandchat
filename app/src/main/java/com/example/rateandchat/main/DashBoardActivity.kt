package com.example.rateandchat.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import com.example.rateandchat.*
import com.example.rateandchat.profile.MyPageActivity
import com.example.rateandchat.profile.UserListActivity
import com.example.rateandchat.programs.AllProgramsActivity
import com.example.rateandchat.sports.SportsActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class DashBoardActivity : BasicActivity() {
    lateinit var auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash_board)
        auth = Firebase.auth
    }


    fun openSportsActivity (view: View){
        val intent = Intent(this, SportsActivity::class.java)
        startActivity(intent)
    }

    fun openChatActivity (view: View) {
        val intent = Intent(this, UserListActivity::class.java)
        startActivity(intent)
    }

    fun openAllProgramsActivity (view: View){
        val intent = Intent(this, AllProgramsActivity::class.java)
        startActivity(intent)
    }
}