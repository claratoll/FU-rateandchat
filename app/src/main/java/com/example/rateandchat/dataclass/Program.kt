package com.example.rateandchat.dataclass

import com.google.firebase.firestore.DocumentId
import java.util.Date

data class Program(
    @DocumentId val documentId: String = "",
    val name :String? = null,
    val image : Int? = null,
    val Date : Date? = null,
    val description : String? = null)
