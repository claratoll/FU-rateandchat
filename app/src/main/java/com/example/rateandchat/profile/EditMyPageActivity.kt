package com.example.rateandchat.profile

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.rateandchat.BasicActivity
import com.example.rateandchat.R
import com.example.rateandchat.dataclass.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class EditMyPageActivity : BasicActivity() {
    lateinit var userImage : ImageView
    private var imageUri : Uri? = null
    lateinit var storageRef : StorageReference
    lateinit var db : FirebaseFirestore
    lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_my_page)



        userImage = findViewById(R.id.pickImageIV)
        val save = findViewById<Button>(R.id.saveButton)
        save.setOnClickListener{
            uploadImage()
            val intent = Intent(this, MyPageActivity::class.java)
            startActivity(intent)
        }

        storageRef = FirebaseStorage.getInstance().reference.child("Images")
        db = FirebaseFirestore.getInstance()

        userImage.setOnClickListener{
            resultLauncher.launch("image/*")
        }
    }
    // to let the user chose an image from mobiles internal storage
    private val resultLauncher = registerForActivityResult(
        ActivityResultContracts.GetContent()){
        imageUri = it
        userImage.setImageURI(it)
    }

// to upload the image to dataBase.
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
                        sendUriToUserDoc(currentUser, profilePicUri)
                    }
                }
                else{
                    Toast.makeText(this, task.exception?.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    /* Updates user in Users collection
    * with profile picture uploaded
    * from storage in Uri format. */
    private fun sendUriToUserDoc(currentUser : FirebaseUser, profilePicUri : ProfilePic) {
        db.collection("Users")
            .whereEqualTo("uid", currentUser.uid)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    Log.d("uid_query", "${document.id} => ${document.data}")
                    val userDocId = document.toObject<User>().documentId

                    db.collection("Users").document(userDocId)
                        .update("profilePic", profilePicUri.profileImage)
                    Log.d("user_pic", "Profile pic with Uri ${profilePicUri.toString()} added to user.")

                }
            }
            .addOnFailureListener { e ->
                Log.d("uid_query", "Uid not found.")
            }
    }
}
data class ProfilePic(val profileImage : String? = null)