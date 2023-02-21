package com.example.rateandchat.programs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.rateandchat.R

const val POSITION_KEY = "position"
const val POSITION_NOT_SET = -1
class ProgramInfo : AppCompatActivity() {
    lateinit var heading : TextView
    lateinit var image : ImageView
    lateinit var description : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_program_info)

        val position = intent.getIntExtra(POSITION_KEY, POSITION_NOT_SET)

        heading = findViewById(R.id.headingTV)
        image = findViewById(R.id.imageIV)
        description = findViewById(R.id.descriptionTV)

     /*   val program = ProgramDataManager.programs[position]
        heading.text = program.name
        image.setImageResource(program.image!!)
        description.text = program.description*/
    }
}