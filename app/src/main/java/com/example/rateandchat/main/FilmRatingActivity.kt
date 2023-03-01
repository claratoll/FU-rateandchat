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

    lateinit var backBtn : Button
    lateinit var plotRating : RatingBar
    lateinit var actingRating : RatingBar
    lateinit var writingRating : RatingBar
    lateinit var directingRating : RatingBar
    lateinit var soundtrackRating : RatingBar
    lateinit var userAvgRating : RatingBar
    lateinit var filmTitle : TextView
    lateinit var db : FirebaseFirestore
    lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_film_rating)


        db = Firebase.firestore
        auth = FirebaseAuth.getInstance()


        backBtn = findViewById(R.id.backToContent)
        filmTitle = findViewById(R.id.filmTitleTextView)
        plotRating = findViewById(R.id.plotUserRating)
        actingRating = findViewById(R.id.actingUserRating)
        writingRating = findViewById(R.id.writingUserRating)
        directingRating = findViewById(R.id.directingUserRating)
        soundtrackRating = findViewById(R.id.soundtrackUserRating)
        userAvgRating = findViewById(R.id.userAvgRatingBar)

        userAvgRating.rating = (plotRating.rating + actingRating.rating + writingRating.rating + directingRating.rating + soundtrackRating.rating)/5

        val itemRatingList = arrayListOf(plotRating.rating, actingRating.rating, writingRating.rating, directingRating.rating, soundtrackRating.rating)
        val usersRef = db.collection("Users")
        val currentUid = auth.currentUser!!.uid
        val name = intent.getStringExtra("name")
        val documentId = intent.getStringExtra("documentId")

        var currentUserDocumentId = "jFBmJMDxsr9ctuf6zk8"
//        val docRef = db.collection("Users").whereEqualTo("uid", auth.currentUser?.uid).limit(1)
//        docRef.get()
//            .addOnSuccessListener { documents ->
//                for (document in documents) {
//                    currentUserDocumentId = document.id
//                }
//            }
//            .addOnFailureListener { e ->
//                Log.d("uid", " ERROR!")
//            }

        var currentUserRef = db.collection("Users").document(currentUserDocumentId)
        val currentFilmRef = currentUserRef.collection("myFilmRatings")


        filmTitle.text = name
        Log.d("filmId", "film doc id is $documentId")

        val filmRating = FilmRating(documentId, name,
                                    plotRating.rating,
                                    actingRating.rating,
                                    writingRating.rating,
                                    directingRating.rating,
                                    soundtrackRating.rating)

        currentFilmRef.add(filmRating)
            .addOnSuccessListener { document ->
                Log.d("ratingAdd", "Ratings added, doc created")
            }
            .addOnFailureListener { e ->
                Log.d("ratingAdd", "error")
            }

        plotRating.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
            ratingValues()
            itemRatingList[0] = rating
            updateFilmRatings(documentId!!, name!!, currentFilmRef)

        }
        actingRating.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
            ratingValues()
            itemRatingList[1] = rating
            updateFilmRatings(documentId!!, name!!, currentFilmRef)
        }
        writingRating.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
            ratingValues()
            itemRatingList[2] = rating
            updateFilmRatings(documentId!!, name!!, currentFilmRef)
        }
        directingRating.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
            ratingValues()
            itemRatingList[3] = rating
            updateFilmRatings(documentId!!, name!!, currentFilmRef)
        }
        soundtrackRating.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
            ratingValues()
            itemRatingList[4] = rating
            updateFilmRatings(documentId!!, name!!, currentFilmRef)
        }

        currentFilmRef.add(filmRating)
            .addOnSuccessListener {documentReference ->
                Log.d("docId", "DocumentSnapshot written with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.d("docId", "Document not created.")
            }

        backBtn.setOnClickListener {
            finish()
        }
    }

    fun ratingValues() {
        val plotRatingValue = plotRating.rating
        val actingRatingValue = actingRating.rating
        val writingRatingValue = writingRating.rating
        val directingRatingValue = directingRating.rating
        val soundtrackRatingValue = soundtrackRating.rating
        val averageRating = (plotRatingValue + actingRatingValue + writingRatingValue + directingRatingValue + soundtrackRatingValue) / 5.0f
        userAvgRating.rating = averageRating
    }

    fun updateFilmRatings(documentId : String, name : String, currentFilmRef : CollectionReference) {
        val updatedFilmRating = FilmRating(documentId, name,
            plotRating.rating,
            actingRating.rating,
            writingRating.rating,
            directingRating.rating,
            soundtrackRating.rating)

        currentFilmRef.document(documentId).set(updatedFilmRating)
            .addOnSuccessListener { documentReference ->
                Log.d("docId", "DocumentSnapshot written with ID: $documentReference")
            }
            .addOnFailureListener { e ->
                Log.d("docId", "Document not updated.")
            }
    }

}

