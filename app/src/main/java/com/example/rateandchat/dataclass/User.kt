package com.example.rateandchat.dataclass

import com.google.firebase.firestore.DocumentId

data class User (
    @DocumentId val documentId: String = "",
    var userName : String? = null, // username is the users email for now
    var firstName : String? = null // firstName is users real name and the name that will be shown
        )