package com.example.rateandchat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rateandchat.dataclass.User
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class UserListActivity : AppCompatActivity() {

    private lateinit var userRecyclerView : RecyclerView
    private lateinit var userList: ArrayList<User>
    private lateinit var adapter : UserAdapter
    private lateinit var auth : FirebaseAuth
    private lateinit var db : FirebaseFirestore
    private lateinit var usersRef : CollectionReference
    private lateinit var generalChatFab : FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)

        //gömmer Action Bar längst upp
        supportActionBar?.hide()

        auth = FirebaseAuth.getInstance()
        db = Firebase.firestore
        usersRef = db.collection("Users")

        userList = ArrayList()
        adapter = UserAdapter(this, userList)

        userRecyclerView = findViewById(R.id.userRecyclerView)
        generalChatFab = findViewById(R.id.generalChatFab)

        userRecyclerView.layoutManager = LinearLayoutManager(this)
        userRecyclerView.adapter = adapter

        generalChatFab.setOnClickListener {
            val intent = Intent(this, GeneralChatActivity::class.java)
            startActivity(intent)
        }

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