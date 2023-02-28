package com.example.rateandchat.sports

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rateandchat.R
import com.example.rateandchat.dataclass.Team
import com.squareup.picasso.Picasso


class TeamRecyclerAdapter (val context: Context, val teams : List <Team>): RecyclerView.Adapter<TeamRecyclerAdapter.ViewHolder>() {

    val layoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder (parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = layoutInflater.inflate(R.layout.team_item, parent, false)

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val team = teams[position]
        holder.teamNameView.text = team.teamName.toString()
        holder.teamNumberView.text = team.teamNumber.toString()
        Picasso.get().load(team.logoImage).into(holder.imageLogoView)
        holder.teamPosition = position
    }


    override fun getItemCount() = teams.size

    inner class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){
        val teamNumberView = itemView.findViewById<TextView>(R.id.rankingNumberTextView)
        val teamNameView = itemView.findViewById<TextView>(R.id.teamNameTextView)
        val imageLogoView = itemView.findViewById<ImageView>(R.id.teamlogoImageView)
        var teamPosition = 0
    }
}