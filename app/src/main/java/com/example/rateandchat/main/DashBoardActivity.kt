package com.example.rateandchat.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.rateandchat.*
import com.example.rateandchat.profile.MyPageActivity
import com.example.rateandchat.profile.UserListActivity
import com.example.rateandchat.sports.SportsActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class DashBoardActivity : AppCompatActivity() {
    lateinit var auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash_board)
        auth = Firebase.auth

        //gömmer Action Bar längst upp
        supportActionBar?.hide()

    }
    fun logOutButton(view: View){
        auth.signOut()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun openMyPageActivity (view: View){
        val intent = Intent(this, MyPageActivity::class.java)
        startActivity(intent)
    }


    fun openTvProgramsActivity (view : View){
        val intent = Intent(this, ProgramsActivity::class.java)
        startActivity(intent)
    }

    fun openSportsActivity (view: View){
        val intent = Intent(this, SportsActivity::class.java)
        startActivity(intent)
    }

    fun openChatActivity (view: View) {
        val intent = Intent(this, UserListActivity::class.java)
        startActivity(intent)
    }
}