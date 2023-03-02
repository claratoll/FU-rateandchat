package com.example.rateandchat.dataclass

import android.media.Image
import com.google.firebase.firestore.DocumentId

data class Team(
    @DocumentId val documentId: String? = "",
    var teamNumber : Int? = 0,
    var teamName : String? = null,
    var logoImage : String? = null,
    var league : String? = null,
    var glideImageUrl: String? = ""
)
