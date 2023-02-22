package com.example.rateandchat.sports

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rateandchat.R
import com.example.rateandchat.dataclass.League
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class LeagueActivity : AppCompatActivity() {

    val listOfLeagues = mutableListOf<League>()
    private lateinit var db: FirebaseFirestore
    lateinit var recyclerView: RecyclerView
    private lateinit var adapter: LeagueAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_league)

        db = Firebase.firestore

        //gömmer Action Bar längst upp
        supportActionBar?.hide()
        recyclerView = findViewById(R.id.leagueRecyclerView)

        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = LeagueAdapter(this, listOfLeagues)
        recyclerView.adapter = adapter

        getLeagueData()
    }


    private fun getLeagueData() {
        db.collection("Leagues")
            .addSnapshotListener { snapshot, e ->
                listOfLeagues.clear()
                if (snapshot != null) {
                    val leagueArray = mutableListOf<League>()
                    for (document in snapshot.documents) {
                        val leagueDoc = document.toObject<League>()
                        if (leagueDoc != null) {
                            leagueArray.add(leagueDoc)
                        } else {
                            Log.v("!!!", "no league")
                        }
                    }

                    listOfLeagues.addAll(leagueArray)
                    recyclerView.adapter?.notifyDataSetChanged()
                }
            }
    }
}