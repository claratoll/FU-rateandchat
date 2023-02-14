package com.example.rateandchat

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import java.net.URI

class EditMyPageActivity : AppCompatActivity() {
    lateinit var userImage : ImageView
    lateinit var userName : EditText
    private var imageUri : Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_my_page)

        //gömmer Action Bar längst upp
        supportActionBar?.hide()

        userImage = findViewById(R.id.pickImageIV)
        userName = findViewById(R.id.userNameET)


    }

    fun imagePicker (view: View){
       val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(gallery, 100)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == 100){
            imageUri = data?.data
            userImage.setImageURI(imageUri)
        }
    }

    fun saveInfo (view: View){
        val name = userName.text.toString()
        val intent = Intent(this, MyPageActivity::class.java)
        intent.putExtra("KEY", name)
        startActivity(intent)
        finish()
    }

}