package com.example.rateandchat.programs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.rateandchat.BasicActivity
import com.example.rateandchat.R
import com.squareup.picasso.Picasso

class ProgramInfo : BasicActivity() {
    lateinit var heading : TextView
    lateinit var image : ImageView
    lateinit var description : TextView
    lateinit var information : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_program_info)


        //show information about the program
        val name = intent.getStringExtra("name")
        val time = intent.getStringExtra("time")
        val getImage = intent.getStringExtra("image")
        val info = intent.getStringExtra("info")

        heading = findViewById(R.id.headingTV)
        image = findViewById(R.id.imageIV)
        description = findViewById(R.id.descriptionTV)
        information = findViewById(R.id.infoTV)

        heading.text = name
        description.text = time
        information.text = info
        Picasso.get().load(getImage).into(image)

    }
}