package com.example.rateandchat.programs

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rateandchat.R
import com.example.rateandchat.programs.ProgramInfo
import com.squareup.picasso.Picasso

class FragmentAdapter(val context: Context?, val programs: List<FragmentDataClass>)
    : RecyclerView.Adapter<FragmentAdapter.ViewHolder>(){

    val layoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView= layoutInflater.inflate(R.layout.list_item, parent, false)
        return ViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val program = programs[position]
        holder.name.text = program.name
        holder.time.text = program.time
        Picasso.get().load(program.image).into(holder.image)
        holder.listItemPosition = position

        holder.itemView.setOnClickListener{
            val intent = Intent(context, ProgramInfo::class.java)
            intent.putExtra("name", program.name)
            intent.putExtra("image", program.image)
            intent.putExtra("time", program.time)
            context!!.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return programs.size
    }

    inner class ViewHolder(itemView : View): RecyclerView.ViewHolder(itemView){
        val name = itemView.findViewById<TextView>(R.id.nameItemTextView)
        val image = itemView.findViewById<ImageView>(R.id.ItemImageView)
        val time = itemView.findViewById<TextView>(R.id.timeTV)
        var listItemPosition = 0

        init {
        }
    }

}