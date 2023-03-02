package com.example.rateandchat.main

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.rateandchat.R
import com.example.rateandchat.dataclass.FilmRating
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FilmRatingActivity : AppCompatActivity() {

    private lateinit var backBtn : Button
    private lateinit var saveBtn : Button
    private lateinit var plotRating : RatingBar
    private lateinit var actingRating : RatingBar
    private lateinit var writingRating : RatingBar
    private lateinit var directingRating : RatingBar
    private lateinit var soundtrackRating : RatingBar
    private lateinit var userAvgRating : RatingBar
    private lateinit var filmTitle : TextView
    lateinit var db : FirebaseFirestore
    lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_film_rating)


        db = Firebase.firestore
        auth = FirebaseAuth.getInstance()


        backBtn = findViewById(R.id.backToContent)
        saveBtn = findViewById(R.id.saveToDbButton)
        filmTitle = findViewById(R.id.filmTitleTextView)
        plotRating = findViewById(R.id.plotUserRating)
        actingRating = findViewById(R.id.actingUserRating)
        writingRating = findViewById(R.id.writingUserRating)
        directingRating = findViewById(R.id.directingUserRating)
        soundtrackRating = findViewById(R.id.soundtrackUserRating)
        userAvgRating = findViewById(R.id.userAvgRatingBar)

        userAvgRating.rating = (plotRating.rating + actingRating.rating + writingRating.rating + directingRating.rating + soundtrackRating.rating)/5

        val ratingsRef = db.collection("FilmRatings")
        val name = intent.getStringExtra("name")
        val documentId = intent.getStringExtra("documentId")


        filmTitle.text = name
        Log.d("filmId", "film doc id is $documentId")

        if (name != null) {
            readOwnRatingsFromDb(name)
        }


        plotRating.setOnRatingBarChangeListener { _, _, _ ->
            ratingValues()
            updateFilmRatings(documentId!!, name!!, ratingsRef)

        }
        actingRating.setOnRatingBarChangeListener { _, _, _ ->
            ratingValues()
            updateFilmRatings(documentId!!, name!!, ratingsRef)
        }
        writingRating.setOnRatingBarChangeListener { _, _, _ ->
            ratingValues()
            updateFilmRatings(documentId!!, name!!, ratingsRef)
        }
        directingRating.setOnRatingBarChangeListener { _, _, _ ->
            ratingValues()
            updateFilmRatings(documentId!!, name!!, ratingsRef)
        }
        soundtrackRating.setOnRatingBarChangeListener { _, _, _ ->
            ratingValues()
            updateFilmRatings(documentId!!, name!!, ratingsRef)
        }

        saveBtn.setOnClickListener {
            updateFilmRatings(documentId!!, name!!, ratingsRef)
        }

        backBtn.setOnClickListener {
            finish()
        }
    }

    private fun ratingValues() {
        val plotRatingValue = plotRating.rating
        val actingRatingValue = actingRating.rating
        val writingRatingValue = writingRating.rating
        val directingRatingValue = directingRating.rating
        val soundtrackRatingValue = soundtrackRating.rating
        val averageRating = (plotRatingValue + actingRatingValue + writingRatingValue + directingRatingValue + soundtrackRatingValue) / 5.0f
        userAvgRating.rating = averageRating
    }

    private fun updateFilmRatings(documentId : String, name : String, ratingsRef : CollectionReference) {
        val updatedFilmRating = FilmRating(documentId, name,
            plotRating.rating,
            actingRating.rating,
            writingRating.rating,
            directingRating.rating,
            soundtrackRating.rating,
            userAvgRating.rating,
            auth.currentUser!!.uid)

        ratingsRef.document(auth.currentUser!!.uid + name).set(updatedFilmRating)
            .addOnSuccessListener { documentReference ->
                Log.d("docId", "DocumentSnapshot written with ID: $documentReference")
            }
            .addOnFailureListener {
                Log.d("docId", "Document not updated.")
            }
    }

    private fun readOwnRatingsFromDb(name : String) {
        val docRef = db.collection("FilmRatings").document(auth.currentUser!!.uid + name)

        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null && document.exists()) {
                    Log.d("readRating", "DocumentSnapshot data: ${document.data}")
                    val data = document.data
                    plotRating.rating = data?.get("plot").toString().toFloat()
                    actingRating.rating = data?.get("acting")?.toString()!!.toFloat()
                    writingRating.rating = data?.get("writing")?.toString()!!.toFloat()
                    directingRating.rating = data?.get("directing").toString().toFloat()
                    soundtrackRating.rating = data?.get("soundtrack").toString().toFloat()
                    userAvgRating.rating = data?.get("average").toString().toFloat()
                } else {
                    Log.d("readRating", "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d("readRating", "get failed with ", exception)
            }

    }

}

