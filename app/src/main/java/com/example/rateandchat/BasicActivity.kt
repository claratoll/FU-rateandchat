package com.example.rateandchat

import android.content.Intent
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.rateandchat.main.MainActivity
import com.example.rateandchat.profile.MyPageActivity
import com.example.rateandchat.profile.UserListActivity
import com.google.firebase.auth.FirebaseAuth

open class BasicActivity : AppCompatActivity(){

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //lateinit var auth : FirebaseAuth
        // Handle item selection
        return when (item.itemId) {
            R.id.logOutOption -> {
                FirebaseAuth.getInstance().signOut()
                //auth.signOut()
                //val intent = Intent(this, MainActivity::class.java)
                //startActivity(intent)
                finishAffinity()
                true
            }
            R.id.profilePage -> {
                val intent = Intent(this, MyPageActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.chat -> {
                val intent = Intent(this, UserListActivity::class.java)
                startActivity(intent)
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }
}
