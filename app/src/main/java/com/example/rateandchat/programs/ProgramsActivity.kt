package com.example.rateandchat.programs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rateandchat.R
import com.example.rateandchat.dataclass.Program
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class ProgramsActivity : AppCompatActivity() {

    private val listOfPrograms = mutableListOf<Program>()


    private lateinit var db: FirebaseFirestore

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ProgramAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_programs)

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

        //get programs from firebase and save it in an array which is shown in the recyclerview

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
                    //programs sorted by date
                    programArray.sortBy { it.Date }

                    listOfPrograms.addAll(programArray)
                    recyclerView.adapter?.notifyDataSetChanged()
                }
            }
    }


}