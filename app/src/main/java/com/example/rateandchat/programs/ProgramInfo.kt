package com.example.rateandchat.programs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.rateandchat.R

class ProgramInfo : AppCompatActivity() {
    lateinit var heading : TextView
    lateinit var image : ImageView
    lateinit var description : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_program_info)


        //show information about the program
        val name = intent.getStringExtra("program")
        val info = intent.getStringExtra("info")
        //val getImage = intent.getExtra("image")

        heading = findViewById(R.id.headingTV)
        image = findViewById(R.id.imageIV)
        description = findViewById(R.id.descriptionTV)

        heading.text = name
        description.text = info

    }
}