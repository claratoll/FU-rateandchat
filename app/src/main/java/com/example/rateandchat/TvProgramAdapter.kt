package com.example.rateandchat

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rateandchat.dataclass.TvProgram

class TvProgramAdapter(val context : Context, val tvPrograms : List<TvProgram>)
                       :RecyclerView.Adapter<TvProgramAdapter.ViewHolder>(){

    val layoutInflater = LayoutInflater.from(context)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView= layoutInflater.inflate(R.layout.list_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tvProgram = tvPrograms[position]
        holder.heading.text = tvProgram.heading
        holder.image.setImageResource(tvProgram.image!!)
        holder.listItemPosition = position
    }

    override fun getItemCount(): Int {
        return tvPrograms.size
    }

    inner class ViewHolder(itemView : View):RecyclerView.ViewHolder(itemView){
        val heading = itemView.findViewById<TextView>(R.id.nameItemTextView)
        val image = itemView.findViewById<ImageView>(R.id.ItemImageView)
        var listItemPosition = 0

        init {
            itemView.setOnClickListener{
                val intent = Intent(context , TvProgramInfo::class.java)
                intent.putExtra(POSITION_KEY, listItemPosition)
                context.startActivity(intent)
            }
        }
    }

}