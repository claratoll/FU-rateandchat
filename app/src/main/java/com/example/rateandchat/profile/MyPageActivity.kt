package com.example.rateandchat.profile

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.rateandchat.BasicActivity
import com.example.rateandchat.Position.ifUserHasSavedSeason
import com.example.rateandchat.R
import com.example.rateandchat.dataclass.User
import com.example.rateandchat.main.DashBoardActivity
import com.example.rateandchat.sports.LeagueActivity
import com.example.rateandchat.sports.SeasonGuessActivity
import com.example.rateandchat.sports.SportsActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso
import org.w3c.dom.Text

class MyPageActivity : BasicActivity() {
    lateinit var personName : TextView
    lateinit var pointsView : TextView
    lateinit var profilePic : ImageView
    lateinit var seasonTipsButton : Button

    lateinit var db :FirebaseFirestore
    private lateinit var usersRef : CollectionReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_page)

        db = Firebase.firestore
        val currentUser = FirebaseAuth.getInstance().currentUser?.uid

        personName = findViewById(R.id.noNameTV)
        profilePic = findViewById(R.id.profileIV)
        pointsView = findViewById(R.id.pointsView)

        downloadImage()


        db.collection("Users").document(currentUser!!).get()
// to get the user name into my page activity.
            .addOnSuccessListener { document ->
                    personName.text = document.toObject<User>()!!.name.toString()
                    pointsView.text = document.toObject<User>()!!.points.toString()
            }


        seasonTipsButton = findViewById(R.id.ToSeasonTipsButton)
        seasonTipsButton.setOnClickListener {
            val intent = Intent(this, LeagueActivity::class.java)
            ifUserHasSavedSeason = true
            this.startActivity(intent)
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
// to get the image from dataBase while using the current user.
    fun downloadImage(){
        val currentUser = FirebaseAuth.getInstance().currentUser?.uid
        db.collection("profile Image").document(currentUser!!).get().addOnSuccessListener {it ->
            var imageUri = it.toObject<ProfilePic>()?.profileImage.toString()
            Picasso.get().load(imageUri).into(profilePic)
        }
    }
}