package com.example.rateandchat.profile

import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.rateandchat.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class EditMyPageActivity : AppCompatActivity() {
    lateinit var userImage : ImageView
    private var imageUri : Uri? = null
    lateinit var storageRef : StorageReference
    lateinit var db : FirebaseFirestore
    lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_my_page)

        //gömmer Action Bar längst upp
        supportActionBar?.hide()

        userImage = findViewById(R.id.pickImageIV)
        val save = findViewById<Button>(R.id.saveButton)
        save.setOnClickListener{
            uploadImage()
            finish()
        }

        storageRef = FirebaseStorage.getInstance().reference.child("Images")
        db = FirebaseFirestore.getInstance()

        userImage.setOnClickListener{
            resultLauncher.launch("image/*")
        }
    }
    private val resultLauncher = registerForActivityResult(
        ActivityResultContracts.GetContent()){
        imageUri = it
        userImage.setImageURI(it)
    }


    private fun uploadImage(){
        storageRef = storageRef.child(System.currentTimeMillis().toString())
        imageUri?.let {
            storageRef.putFile(it).addOnCompleteListener{ task ->
                if (task.isSuccessful){
                    storageRef.downloadUrl.addOnSuccessListener { uri ->
                        auth = Firebase.auth
                        val currentUser = auth.currentUser
                        val profilePicUri = ProfilePic(uri.toString())
                        db.collection("profile Image").document(currentUser!!.uid).set(profilePicUri)
                            .addOnSuccessListener {
                                Toast.makeText(this, "Upload successful!", Toast.LENGTH_SHORT).show()
                            }
                    }
                }
                else{
                    Toast.makeText(this, task.exception?.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}
data class ProfilePic(val profileImage : String? = null)