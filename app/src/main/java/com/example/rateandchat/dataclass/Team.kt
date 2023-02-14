package com.example.rateandchat.dataclass

import android.media.Image
import com.google.firebase.firestore.DocumentId

data class Team(
    @DocumentId val documentId: String = "",
    var teamNumber : Int? = null,
    var teamName : String? = null,
    var logoImage : Image? = null,
    var league : String? = null
)
