package com.example.rateandchat.sports

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rateandchat.R
import com.example.rateandchat.dataclass.League
import com.example.rateandchat.programs.POSITION_KEY
import com.example.rateandchat.programs.ProgramInfo

class LeagueAdapter(val context : Context, val leagues : List<League>)
    : RecyclerView.Adapter<LeagueAdapter.ViewHolder>() {

    val layoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView= layoutInflater.inflate(R.layout.list_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val thisLeague = leagues[position]
        holder.name.text = thisLeague.leagueName.toString()
      //  holder.image.setImageResource(thisLeague.leagueLogo!!)
        holder.listItemPosition = position

        holder.itemView.setOnClickListener{
            val intent = Intent(context , SeasonGuessActivity::class.java)
            intent.putExtra("league name", thisLeague.leagueName.toString())
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return leagues.size
    }

    inner class ViewHolder(itemView : View):RecyclerView.ViewHolder(itemView){
        val name = itemView.findViewById<TextView>(R.id.nameItemTextView)
        val image = itemView.findViewById<ImageView>(R.id.ItemImageView)
        var listItemPosition = 0




    }
}