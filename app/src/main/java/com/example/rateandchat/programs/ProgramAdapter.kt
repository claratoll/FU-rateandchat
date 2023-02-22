package com.example.rateandchat.programs

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rateandchat.programs.ProgramInfo
import com.example.rateandchat.R
import com.example.rateandchat.dataclass.Program
import com.example.rateandchat.sports.GuessResultActivity

class ProgramAdapter(val context : Context, val programs : List<Program>)
                       :RecyclerView.Adapter<ProgramAdapter.ViewHolder>(){

    val layoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView= layoutInflater.inflate(R.layout.list_item, parent, false)
        return ViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val thisProgram = programs[position]
        holder.name.text = thisProgram.name.toString()
     //   holder.image.setImageResource(thisProgram.image!!)
        holder.listItemPosition = position

        holder.itemView.setOnClickListener{
            val intent = Intent(context , ProgramInfo::class.java)
            intent.putExtra("program", thisProgram.name.toString())
            intent.putExtra("info", thisProgram.description.toString())
            //intent.putExtra("image", thisProgram.image)
            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return programs.size
    }

    inner class ViewHolder(itemView : View):RecyclerView.ViewHolder(itemView){
        val name = itemView.findViewById<TextView>(R.id.nameItemTextView)
        val image = itemView.findViewById<ImageView>(R.id.ItemImageView)
        var listItemPosition = 0
    }

}