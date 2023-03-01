package com.example.rateandchat.programs

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.rateandchat.BasicActivity
import com.example.rateandchat.R
import com.example.rateandchat.chat.GeneralChatActivity
import com.example.rateandchat.main.FilmRatingActivity
import com.squareup.picasso.Picasso

class ProgramInfo : BasicActivity() {
    lateinit var heading : TextView
    lateinit var image : ImageView
    lateinit var description : TextView

    lateinit var chatBtn : Button
    lateinit var voteBtn : Button

    lateinit var information : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_program_info)


        //show information about the program
        val documentId = intent.getStringExtra("documentId")
        val name = intent.getStringExtra("name")
        val time = intent.getStringExtra("time")
        val getImage = intent.getStringExtra("image")
        val info = intent.getStringExtra("info")

        heading = findViewById(R.id.headingTV)
        image = findViewById(R.id.imageIV)
        description = findViewById(R.id.descriptionTV)

        chatBtn = findViewById(R.id.chatButton)
        voteBtn = findViewById(R.id.programVoteButton)

        information = findViewById(R.id.infoTV)


        heading.text = name
        description.text = time
        information.text = info
        Picasso.get().load(getImage).into(image)

        chatBtn.setOnClickListener {
            val intent = Intent(this, GeneralChatActivity::class.java)
            intent.putExtra("roomName", name)
            intent.putExtra("documentId", documentId)
            startActivity(intent)
        }

        voteBtn.setOnClickListener {
            val intent = Intent(this, FilmRatingActivity::class.java)
            intent.putExtra("name", name)
            intent.putExtra("documentId", documentId)
            startActivity(intent)
        }

    }
}