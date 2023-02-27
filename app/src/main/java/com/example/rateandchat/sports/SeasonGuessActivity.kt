package com.example.rateandchat.sports

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Selection.moveUp
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rateandchat.BasicActivity
import com.example.rateandchat.R
import com.example.rateandchat.dataclass.Team
import com.example.rateandchat.profile.ProfilePic
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso

class SeasonGuessActivity : BasicActivity() {

    val listOfTeams = mutableListOf<Team>()

    private lateinit var db: FirebaseFirestore

    private var leagueID = ""

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TeamRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_season_guess)

        db = Firebase.firestore


        recyclerView = findViewById(R.id.teamRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = TeamRecyclerAdapter(this, listOfTeams)
        recyclerView.adapter = adapter

        getTeamData()

        //leagueID is stored in a variable which will be used to sort the teams correctly
        leagueID = intent.getStringExtra("league name")!!
    }

    fun teamLists() {
        //idk here we could have arrays of the teams that are standard?
    }

    fun moveTeams(teamA : Int, teamB : Int) {

            fun <T> MutableList<T>.swap(index1: Int, index2: Int) {
                val tmp = this[index1]
                this[index1] = this[index2]
                this[index2] = tmp

                Log.v("!!!", "move")

            }
        listOfTeams.swap(teamA, teamB)
        adapter.notifyDataSetChanged()
        }
        //and then this function where the user moves the teams in what other he thinks the teams will end in the end of the seaso

    fun saveToFirebase(){
        //and then the user uploads his results to the firebase
    }

    fun moveUpClick(view: View){

        val teamA = 1
        val teamB = 0

        moveTeams(teamA, teamB)
        Log.v("!!!", "move up")
    }

    fun moveDownClick(view: View){

        val teamA = 2
        val teamB = 1

        moveTeams(teamA, teamB)
        Log.v("!!!", "move down")
    }


    private fun getTeamData(){

        //get teams from firebase and save it in an array which is shown in the recyclerview

        db.collection("Team")
                .addSnapshotListener { snapshot, e ->
                    listOfTeams.clear()
                    if (snapshot != null) {
                        val teamArray = mutableListOf<Team>()
                        for (document in snapshot.documents) {
                            val teamDoc = document.toObject<Team>()
                            Log.v("!!!", teamDoc?.teamName.toString())
                            if (teamDoc != null) {

                                //if the team has the same leagueID as stored above it is saved in the array
                                if (teamDoc.league.equals(leagueID)){
                                    teamArray.add(teamDoc)
                                }
                            } else {
                                Log.v("!!!", "no info")
                            }
                        }
                        teamArray.sortBy { it.teamNumber }

                        listOfTeams.addAll(teamArray)
                        adapter.notifyDataSetChanged()
                    }
        }
    }
}