package com.example.rateandchat.dataclass

import com.google.firebase.firestore.DocumentId
import java.util.Date

data class Game (
    @DocumentId val documentId: String = "",
    var teamAPlaying : String? = null,
    var teamBPlaying : String? = null,
    var score : String? = null,
    var image : String? = null,
    var tag : String? = null,
    var Date : Date? = null
        )