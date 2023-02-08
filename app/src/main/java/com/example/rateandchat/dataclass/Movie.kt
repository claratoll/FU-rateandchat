package com.example.rateandchat.dataclass
import android.widget.ImageView

import com.google.firebase.firestore.DocumentId


data class Movie (
    @DocumentId val documentId: String = "",
    var movieName : String? = null,
    var points : Int? = null, // all points all users submitted together
    var userPoints : Int? = null // points the user submitted
        )

