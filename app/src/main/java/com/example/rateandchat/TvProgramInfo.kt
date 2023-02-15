package com.example.rateandchat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

const val POSITION_KEY = "position"
const val POSITION_NOT_SET = -1
class TvProgramInfo : AppCompatActivity() {
    lateinit var heading : TextView
    lateinit var image : ImageView
    lateinit var description : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tv_program_info)

        val position = intent.getIntExtra(POSITION_KEY, POSITION_NOT_SET)

        heading = findViewById(R.id.headingTV)
        image = findViewById(R.id.imageIV)
        description = findViewById(R.id.descriptionTV)

        val tvProgram = TvProgramDataManager.tvPrograms[position]
        heading.text = tvProgram.heading
        image.setImageResource(tvProgram.image!!)
        description.text = tvProgram.description
    }
}