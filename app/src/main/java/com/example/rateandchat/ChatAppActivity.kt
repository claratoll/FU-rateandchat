package com.example.rateandchat

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rateandchat.dataclass.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase


// Shows all users as chat contact list. Click on a user to start chatting (opens chat activity).
class ChatApp : AppCompatActivity() {

    private lateinit var userRecyclerView: RecyclerView
    private lateinit var userList: ArrayList<User>
    private lateinit var adapter: UserAdapter // build UserAdapter from chat app
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private lateinit var usersRef: CollectionReference
    //@SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_app)

        auth = FirebaseAuth.getInstance()
        db = Firebase.firestore
        usersRef = db.collection("Users")

        userList = ArrayList()
        adapter = UserAdapter(this, userList)

        userRecyclerView = findViewById(R.id.userRecyclerView)//missinflated ID added, but do not understand why it's needed.

        userRecyclerView.layoutManager = LinearLayoutManager(this)
        userRecyclerView.adapter = adapter

        usersRef.addSnapshotListener() { snapshot, e ->
            if (snapshot != null) {
                userList.clear()
                for (document in snapshot.documents) {
                    val item = document.toObject<User>()
                    Log.d("uidTest", "Logged in user id: ${auth.currentUser?.uid}; Item uid: ${item?.uid}")
                    if (auth.currentUser?.uid != item?.uid) {
                        if (item != null) {
                            userList.add(item)
                        }
                    }
                }
                adapter.notifyDataSetChanged()
            }

        }

    }
}