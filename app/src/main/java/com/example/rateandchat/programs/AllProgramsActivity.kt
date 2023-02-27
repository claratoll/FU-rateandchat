package com.example.rateandchat.programs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import com.example.rateandchat.BasicActivity
import com.example.rateandchat.R
import com.example.rateandchat.main.MainActivity
import com.example.rateandchat.profile.MyPageActivity
import com.example.rateandchat.profile.UserListActivity
import com.google.firebase.auth.FirebaseAuth

class AllProgramsActivity : BasicActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_programs)
        //starts monButton
        val monButton = findViewById<ImageButton>(R.id.monButton)
        monButton.setOnClickListener{
            replaceFragment(MonFragment())
        }
        //starts tusButton
        val tueButton = findViewById<ImageButton>(R.id.tueButton)
        tueButton.setOnClickListener{
            replaceFragment(TueFragment())
        }
        //starts wedButton
        val wedButton = findViewById<ImageButton>(R.id.wedButton)
        wedButton.setOnClickListener{
            replaceFragment(wedFragment())
        }
        //starts thuButton
        val thuButton = findViewById<ImageButton>(R.id.thuButton)
        thuButton.setOnClickListener{
            replaceFragment(thuFragment())
        }
        //starts friButton
        val friButton = findViewById<ImageButton>(R.id.friButton)
        friButton.setOnClickListener{
            replaceFragment(friFragment())
        }
    }

    // a function that replaces a fragments in the container.
    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container,fragment)
        fragmentTransaction.commit()
    }
}