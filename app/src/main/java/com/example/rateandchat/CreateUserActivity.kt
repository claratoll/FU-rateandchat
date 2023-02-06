package com.example.rateandchat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class CreateUserActivity : AppCompatActivity() {

    lateinit var auth : FirebaseAuth
    lateinit var emailView : EditText
    lateinit var passwordView : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_user)

        //login to app
        auth = Firebase.auth

        emailView = findViewById(R.id.createEditEmailView)
        passwordView = findViewById(R.id.createEditPasswordText)

        val signUpButton = findViewById<Button>(R.id.createUserButton)
        signUpButton.setOnClickListener {
            signUp()
        }
    }

    fun signUp(){
        val email = emailView.text.toString()
        val password = passwordView.text.toString()

        if (email.isEmpty() || password.isEmpty()) {
            return
        }

        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener{ task ->
            if (task.isSuccessful){
                Log.d("!!!", "create success")
                //goToAddActivity()
            } else {
                Log.d("!!!", "user not created ${task.exception}")
            }
        }
    }
}

//Här är jag Sita