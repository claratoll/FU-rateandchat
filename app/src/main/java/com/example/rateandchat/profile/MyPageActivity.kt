package com.example.rateandchat.profile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.rateandchat.main.DashBoardActivity
import com.example.rateandchat.R
import com.example.rateandchat.dataclass.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.squareup.picasso.Picasso

class MyPageActivity : AppCompatActivity() {
    lateinit var personName : TextView
    lateinit var db :FirebaseFirestore
    private lateinit var usersRef : CollectionReference
    lateinit var profilePic : ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_page)

        //gömmer Action Bar längst upp
        supportActionBar?.hide()

        db = Firebase.firestore
        val currentUser = FirebaseAuth.getInstance().currentUser?.uid
        personName = findViewById(R.id.noNameTV)
        profilePic = findViewById(R.id.profileIV)
        downloadImage()
        usersRef = db.collection("Users")

        usersRef.whereEqualTo("uid", currentUser)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    personName.text = document.toObject<User>().name.toString()
                }
            }

    }

    fun doneButton (view: View){
        val intent = Intent(this, DashBoardActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun editMyPageActivity(view: View){
        val intent = Intent(this, EditMyPageActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun downloadImage(){
        val currentUser = FirebaseAuth.getInstance().currentUser?.uid
        db.collection("profile Image").document(currentUser!!).get().addOnSuccessListener {it ->
            var imageUri = it.toObject<ProfilePic>()?.profileImage.toString()
            Picasso.get().load(imageUri).into(profilePic)
        }
    }
}