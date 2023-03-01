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
import kotlinx.coroutines.tasks.await

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

        val itemRatingList = arrayListOf(plotRating.rating, actingRating.rating, writingRating.rating, directingRating.rating, soundtrackRating.rating)

        backBtn = findViewById(R.id.backToContent)
        filmTitle = findViewById(R.id.filmTitleTextView)
        plotRating = findViewById(R.id.plotUserRating)
        actingRating = findViewById(R.id.actingUserRating)
        writingRating = findViewById(R.id.writingUserRating)
        directingRating = findViewById(R.id.directingUserRating)
        soundtrackRating = findViewById(R.id.soundtrackUserRating)
        userAvgRating = findViewById(R.id.userAvgRatingBar)

        userAvgRating.rating = (plotRating.rating + actingRating.rating + writingRating.rating + directingRating.rating + soundtrackRating.rating)/5

        val usersRef = db.collection("Users")
        val currentUid = auth.currentUser!!.uid
        val currentUserDocumentId = getCurrentUserDocId()
        val name = intent.getStringExtra("name")
        val documentId = intent.getStringExtra("documentId")
        val currentFilmRef = usersRef.document(currentUserDocumentId!!)

        currentFilmRef.collection("myFilmRatings")
        filmTitle.text = name

        val filmRating = FilmRating(documentId, name,
                                    plotRating.rating,
                                    actingRating.rating,
                                    writingRating.rating,
                                    directingRating.rating,
                                    soundtrackRating.rating)

        plotRating.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
            ratingValues()
            itemRatingList[0] = rating
            updateFilmRatings(documentId!!, name!!, currentFilmRef.collection("myFilmRatings"))

        }
        actingRating.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
            ratingValues()
            itemRatingList[1] = rating
            updateFilmRatings(documentId!!, name!!, currentFilmRef.collection("myFilmRatings"))
        }
        writingRating.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
            ratingValues()
            itemRatingList[2] = rating
            updateFilmRatings(documentId!!, name!!, currentFilmRef.collection("myFilmRatings"))
        }
        directingRating.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
            ratingValues()
            itemRatingList[3] = rating
            updateFilmRatings(documentId!!, name!!, currentFilmRef.collection("myFilmRatings"))
        }
        soundtrackRating.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
            ratingValues()
            itemRatingList[4] = rating
            updateFilmRatings(documentId!!, name!!, currentFilmRef.collection("myFilmRatings"))
        }

        currentFilmRef.collection("myFilmRatings").add(filmRating)
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

    fun getCurrentUserDocId(): String {
        val auth = FirebaseAuth.getInstance()
        val uid = auth.currentUser?.uid
        val db = FirebaseFirestore.getInstance()

        val docRef = db.collection("users").whereEqualTo("uid", uid).limit(1)

        var docId = ""
        docRef.get().addOnSuccessListener { documents ->
            for (doc in documents) {
                docId = doc.id
            }
        }

        return docId
    }
}