package com.example.rateandchat.dataclass

import com.google.firebase.firestore.DocumentId

data class Program(
    @DocumentId val documentId: String = "",
    val name :String? = null,
    val image : Int? = null,
    val description : String? = null)
