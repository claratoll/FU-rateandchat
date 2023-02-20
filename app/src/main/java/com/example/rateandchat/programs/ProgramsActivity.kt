package com.example.rateandchat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rateandchat.dataclass.Program
import com.example.rateandchat.programs.ProgramAdapter
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class ProgramsActivity : AppCompatActivity() {

    val listOfPrograms = mutableListOf<Program>()


    private lateinit var db: FirebaseFirestore

    lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ProgramAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tv_programs)

        db = Firebase.firestore

        //gömmer Action Bar längst upp
        supportActionBar?.hide()

        recyclerView = findViewById(R.id.tvProgramRV)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = ProgramAdapter(this, listOfPrograms)
        recyclerView.adapter = adapter

        getProgramData()

    }


    private fun getProgramData() {
        db.collection("Tv programs")
            .addSnapshotListener { snapshot, e ->
                listOfPrograms.clear()
                if (snapshot != null) {
                    val programArray = mutableListOf<Program>()
                    for (document in snapshot.documents) {
                        val progDoc = document.toObject<Program>()
                        if (progDoc != null) {
                            Log.v("!!!", "program: ${progDoc.name}")
                            programArray.add(progDoc)
                        } else {
                            Log.v("!!!", "no programs")
                        }
                    }

                    listOfPrograms.addAll(programArray)
                    recyclerView.adapter?.notifyDataSetChanged()
                }
            }
    }


  /*  override fun onResume() {
        super.onResume()

        recyclerView.adapter?.notifyDataSetChanged()
    }
*/
}


object ProgramDataManager {
    val programs = mutableListOf<Program>()
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
        // tvPrograms.add(Program("Idol", R.drawable.idol, idolDescription))
        // tvPrograms.add(Program("Talang Svergie", R.drawable.talang, talangDescription))
        // val db : FirebaseFirestore = Firebase.firestore
        //db.collection("Tv programs").add(TvProgram("Idol", R.drawable.idol, idolDescription))
        // db.collection("Tv programs").add(TvProgram("Talang Svergie", R.drawable.talang, talangDescription))
    }
}