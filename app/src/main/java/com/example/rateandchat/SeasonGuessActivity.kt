package com.example.rateandchat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.rateandchat.R
import com.example.rateandchat.dataclass.Team

class SeasonGuessActivity : AppCompatActivity() {

    val listOfTeams = mutableListOf<Team>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_season_guess)

        //gömmer Action Bar längst upp
        supportActionBar?.hide()
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

    private fun getTeamData(){
        /*db.collection("meals")
                .addSnapshotListener { snapshot, e ->
                    mealList.clear()
                    if (snapshot != null) {
                        val mealArray = mutableListOf<Meal>()
                        for (document in snapshot.documents) {
                            val mealDoc = document.toObject<Meal>()
                            if (mealDoc != null) {
                                Log.v("!!!", "meal: ${mealDoc.restaurantID}")
                                if (mealDoc.restaurantID.equals(restaurantId)){
                                mealArray.add(mealDoc)
                                }
                            } else {

                                Log.v("!!!", "no images")
                            }
                        }
                        mealList.addAll(mealArray)
                        adapter.notifyDataSetChanged()

                    }
        }*/
    }



}