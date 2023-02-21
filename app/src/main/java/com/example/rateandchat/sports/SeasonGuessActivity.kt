package com.example.rateandchat.sports

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rateandchat.R
import com.example.rateandchat.dataclass.Team
import com.example.rateandchat.profile.ProfilePic
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso

class SeasonGuessActivity : AppCompatActivity() {

    val listOfTeams = mutableListOf<Team>()

    private lateinit var db: FirebaseFirestore

    private var leagueID = ""

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter : TeamRecyclerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_season_guess)

        db = Firebase.firestore

        //gömmer Action Bar längst upp
        supportActionBar?.hide()

        recyclerView = findViewById(R.id.teamRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = TeamRecyclerAdapter(this, listOfTeams)
        recyclerView.adapter = adapter

        getTeamData()

        //tillfälligt
        leagueID = "SDHL"

       // leagueID = intent.getStringExtra("documentID")!!
    }


    fun teamLists(){

        //idk here we could have arrays of the teams that are standard?
    }

    fun moveTeams(){
        //and then this function where the user moves the teams in what other he thinks the teams will end in the end of the season
    }

    fun saveToFirebase(){
        //and then the user uploads his results to the firebase
    }

    fun moveUpClick(view: View){
        Log.v("!!!", "move up")
    }

    fun moveDownClick(view: View){
        Log.v("!!!", "move down")
    }


    private fun getTeamData(){
        db.collection("Team")
                .addSnapshotListener { snapshot, e ->
                    listOfTeams.clear()
                    if (snapshot != null) {
                        val teamArray = mutableListOf<Team>()
                        for (document in snapshot.documents) {
                            val teamDoc = document.toObject<Team>()
                            if (teamDoc != null) {
                                Log.v("!!!", "team: ${teamDoc.league}")
                                if (teamDoc.league.equals(leagueID)){
                                teamArray.add(teamDoc)
                                }
                            } else {

                                Log.v("!!!", "no images")
                            }
                        }

                        teamArray.sortBy { it.teamNumber }


                        listOfTeams.addAll(teamArray)
                        adapter.notifyDataSetChanged()
                    }
        }
    }
}