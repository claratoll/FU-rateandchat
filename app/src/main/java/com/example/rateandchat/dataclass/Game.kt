package com.example.rateandchat.dataclass

import com.google.firebase.firestore.DocumentId

data class Game (
    @DocumentId val documentId: String = "",
    var teamAPlaying : String? = null,
    var teamBPlaying : String? = null,
    var score : String? = null
        )