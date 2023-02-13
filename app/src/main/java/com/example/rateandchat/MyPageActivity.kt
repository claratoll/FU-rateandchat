package com.example.rateandchat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MyPageActivity : AppCompatActivity() {
    lateinit var personName : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_page)

        //gömmer Action Bar längst upp
        supportActionBar?.hide()

        personName = findViewById(R.id.noNameTV)
        val getName = intent.getStringExtra("KEY")
        personName.text = getName

    }

    fun doneButton (view: View){
        val intent = Intent(this, DashBoardActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun editMyPageActivity(view: View){
        val intent = Intent(this,EditMyPageActivity::class.java)
        startActivity(intent)
        finish()
    }
}