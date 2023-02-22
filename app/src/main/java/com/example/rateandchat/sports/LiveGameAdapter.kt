package com.example.rateandchat.sports

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rateandchat.R
import com.example.rateandchat.dataclass.Game

class LiveGameAdapter (val context: Context, val games : List <Game>): RecyclerView.Adapter<LiveGameAdapter.ViewHolder>() {

    val layoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder (parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = layoutInflater.inflate(R.layout.list_item, parent, false)

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val game = games[position]
        holder.teamNameView.text = "${game.teamAPlaying} vs ${game.teamBPlaying}: ${game.Date}"
        //holder.imageLogoView.setImageResource(team.logoImage!!)
        holder.teamPosition = position

        holder.itemView.setOnClickListener{
            val intent = Intent(context , GuessResultActivity::class.java)
            intent.putExtra("teamAPlaying", game.teamAPlaying.toString())
            intent.putExtra("teamBPlaying", game.teamBPlaying.toString())
            context.startActivity(intent)
        }
    }


    override fun getItemCount() = games.size

    inner class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){
        val teamNameView = itemView.findViewById<TextView>(R.id.nameItemTextView)
        val imageLogoView = itemView.findViewById<ImageView>(R.id.ItemImageView)
        var teamPosition = 0

    }


}