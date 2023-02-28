package com.example.rateandchat.dataclass

import com.google.firebase.firestore.DocumentId

data class User (
    var name : String? = null, //need to check if we can use it like this and can cange userName to email. integration of Chat funktion
    var email : String? = null,
    var uid : String? = null,
    var profilePic : String? = null,
    @DocumentId val documentId: String = "",
    var userName : String? = null, // username is the users email for now
    var firstName : String? = null, // firstName is users real name and the name that will be shown
    var points : Int? = 0

        )