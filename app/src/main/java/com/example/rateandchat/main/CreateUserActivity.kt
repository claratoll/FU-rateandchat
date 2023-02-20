package com.example.rateandchat.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.rateandchat.R
import com.example.rateandchat.dataclass.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CreateUserActivity : AppCompatActivity() {

    lateinit var auth : FirebaseAuth
    lateinit var nameView : EditText
    lateinit var emailView : EditText
    lateinit var passwordView : EditText
    lateinit var db : FirebaseFirestore
    lateinit var usersRef : CollectionReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_user)

        //gömmer Action Bar längst upp
        supportActionBar?.hide()

        //login to app
        auth = Firebase.auth

        // Initialize firestore
        db = Firebase.firestore
        usersRef = db.collection("Users")

        nameView = findViewById(R.id.createNameEditView)
        emailView = findViewById(R.id.createEditEmailView)
        passwordView = findViewById(R.id.createEditPasswordText)
        //nameView = findViewById(R.id.createEditName)

    }

    fun signUp (view: View){
        val name = nameView.text.toString()
        val email = emailView.text.toString()
        val password = passwordView.text.toString()

        if (email.isEmpty() || password.isEmpty() || name.isEmpty()) {
            return
        }

        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener{ task ->
            if (task.isSuccessful){
                Log.d("!!!", "create success")
                addUserToDatabase(name, email, auth.currentUser?.uid!!)
                Log.d("createdUid", "User created with uid: ${auth.currentUser!!.uid}")
                goToDashBoardActivity()

            } else {
                Log.d("!!!", "user not created ${task.exception}")
            }
        }
    }
    fun goToDashBoardActivity (){
        val intent = Intent(this, DashBoardActivity::class.java)
        startActivity(intent)
    }

    // Adds user to firestore with its own uid (from auth.currentUser.uid)
    private fun addUserToDatabase(name : String, email : String, uid : String) {
        usersRef.add(User(name, email, uid))
            .addOnSuccessListener { documentReference ->
                Toast.makeText(this@CreateUserActivity, "DocumentSnapshot added with ID: ${documentReference.id}", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this@CreateUserActivity, "Error handling document", Toast.LENGTH_SHORT).show()
            }
    }
}
