package com.example.rateandchat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rateandchat.dataclass.TvProgram
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class TvProgramsActivity : AppCompatActivity() {
    lateinit var db : FirebaseFirestore
    lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tv_programs)

        recyclerView = findViewById(R.id.tvProgramRV)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = TvProgramAdapter(this, TvProgramDataManager.tvPrograms)
        db = Firebase.firestore
        /*val docRef = db.collection("Tv programs")
        docRef.get().addOnSuccessListener { documentSnapShot->
            for (document in documentSnapShot.documents){
                val tvProgram = document.toObject<TvProgram>()
                if (tvProgram != null){
                    TvProgramDataManager.tvPrograms.add(tvProgram)
                }
            }

        }*/


        //gömmer Action Bar längst upp
        supportActionBar?.hide()
    }

    override fun onResume() {
        super.onResume()

        recyclerView.adapter?.notifyDataSetChanged()
    }
}

object TvProgramDataManager {
    val tvPrograms = mutableListOf<TvProgram>()
    val idolDescription = "Swedish version of Idols, a talent \n" +
            "show in which\n" +
            " amateur singers compete to\n" +
            " determine who is the best."

    val talangDescription = "Talang, formerly named Talang Sverige in\n" +
            "" +
            " 2014, is the Swedish version of the Got Talent series show\n" +
            " where singers, dancers, comedians, variety acts and other\n" +
            " performers compete against each other for audience support\n" +
            " and the prize money"

    init {
       tvPrograms.add(TvProgram("Idol", R.drawable.idol, idolDescription))
       tvPrograms.add(TvProgram("Talang Svergie", R.drawable.talang, talangDescription))
        //val db : FirebaseFirestore = Firebase.firestore
        //db.collection("Tv programs").add(TvProgram("Idol", R.drawable.idol, idolDescription))
       // db.collection("Tv programs").add(TvProgram("Talang Svergie", R.drawable.talang, talangDescription))
    }
}