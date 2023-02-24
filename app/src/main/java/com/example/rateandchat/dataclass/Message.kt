package com.example.rateandchat.dataclass

import com.google.firebase.firestore.ServerTimestamp
import java.util.*

data class Message(
    var message : String? = null,
    var senderId : String? = null,
    var senderName : String? = null,
    var senderProfilePic : String? = null,
    @ServerTimestamp val timestamp : Date? = null
)

