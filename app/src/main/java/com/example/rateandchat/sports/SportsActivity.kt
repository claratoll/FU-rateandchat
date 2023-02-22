package com.example.rateandchat.sports

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rateandchat.R
import com.example.rateandchat.dataclass.Game
import com.example.rateandchat.dataclass.Team
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class SportsActivity : AppCompatActivity() {

    val listOfGames = mutableListOf<Game>()

    private lateinit var db: FirebaseFirestore
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter : LiveGameAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sports)


        db = Firebase.firestore

        recyclerView = findViewById(R.id.gameRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = LiveGameAdapter(this, listOfGames)
        recyclerView.adapter = adapter

        getGameData()

        //gömmer Action Bar längst upp
        supportActionBar?.hide()
    }

    private fun getGameData() {

        //get games from firebase and save it in an array which is shown in the recyclerview

        db.collection("Game")
            .addSnapshotListener { snapshot, e ->
                listOfGames.clear()
                if (snapshot != null) {
                    val gameArray = mutableListOf<Game>()
                    for (document in snapshot.documents) {
                        val gameDoc = document.toObject<Game>()
                        Log.v("!!!", gameDoc?.teamAPlaying.toString())
                        if (gameDoc != null) {
                            gameArray.add(gameDoc)
                        } else {
                            Log.v("!!!", "no info")
                        }
                    }
                    //games sorted by date
                    gameArray.sortBy { it.Date }

                    listOfGames.addAll(gameArray)
                    adapter.notifyDataSetChanged()
                }
            }    }

    fun openLeagueActivity (view: View){
        val intent = Intent(this, LeagueActivity::class.java)
        startActivity(intent)
    }
}