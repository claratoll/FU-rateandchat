package com.example.rateandchat.dataclass

import com.google.firebase.firestore.DocumentId

data class FilmRating (
    @DocumentId var documentId : String? = null,
                var name : String? = null,
                var plot : Float? = null,
                var acting : Float? = null,
                var writing : Float? = null,
                var directing : Float? = null,
                var soundtrack : Float? = null,
                var average : Float? = null,
                var uid : String? = null
                ) {

}
