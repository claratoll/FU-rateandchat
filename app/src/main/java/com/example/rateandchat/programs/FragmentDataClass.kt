package com.example.rateandchat.programs

import com.google.firebase.firestore.DocumentId

// a data class that is used for all the fragments

data class FragmentDataClass(@DocumentId val documentId : String? = null, val name : String? = null, val image : String? = null, val info : String? = null, val time : String? = null)



