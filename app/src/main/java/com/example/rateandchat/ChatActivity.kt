package com.example.rateandchat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rateandchat.dataclass.Message
import com.example.rateandchat.dataclass.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

// 1 on 1 chatroom
class ChatActivity : AppCompatActivity() {

    private lateinit var chatRecyclerView: RecyclerView
    private lateinit var messageBox : EditText
    private lateinit var sendButton : ImageView
    private lateinit var messageAdapter: MessageAdapter
    private lateinit var messageList: ArrayList<Message>
    private lateinit var db : FirebaseFirestore
    private lateinit var messagesRef : CollectionReference
    private lateinit var usersRef : CollectionReference

    var receiverRoom : String? = null
    var senderRoom : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)



        val name = intent.getStringExtra("name")
        val receiverUid = intent.getStringExtra("uid")

        val senderUid = FirebaseAuth.getInstance().currentUser?.uid

        db = Firebase.firestore
        messagesRef = db.collection("Chats")
        usersRef = db.collection("Users")

        senderRoom = receiverUid + senderUid
        receiverRoom = senderUid + receiverUid

        supportActionBar?.title = name

        chatRecyclerView = findViewById(R.id.chatRecycleView)
        messageBox = findViewById(R.id.messageBox)
        sendButton = findViewById(R.id.sendButton)
        messageList = ArrayList()
        messageAdapter = MessageAdapter(this, messageList)
        chatRecyclerView.layoutManager = LinearLayoutManager(this)
        chatRecyclerView.adapter = messageAdapter

        // adding data to recycler view

        messagesRef.document(senderRoom!!).collection("Messages")
            .orderBy("timestamp")
            .addSnapshotListener() { snapshot, e ->
                messageList.clear()
                if (snapshot != null) {
                    for (document in snapshot.documents) {
                        val item = document.toObject<Message>()
                        messageList.add(item!!)
                    }
                    messageAdapter.notifyDataSetChanged()
                }
            }


        // adding the message to database
        sendButton.setOnClickListener {

            val message = messageBox.text.toString()
            usersRef.whereEqualTo("uid", senderUid)
                .get()
                .addOnSuccessListener { documents ->
                    for (document in documents) {
                        Log.d("nameQuery", "${document.id} => ${document.data}")
                        val senderName = document.toObject<User>().name.toString()
                        val messageObject = Message(message, senderUid, senderName)

                        messagesRef.document(senderRoom!!).collection("Messages")
                            .add(messageObject)
                            .addOnSuccessListener { documentReference ->
                                messagesRef.document(receiverRoom!!).collection("Messages")
                                    .add(messageObject)
                                Log.d("msg", "DocumentSnapshot written with ID: ${documentReference.id}")
                                Log.d("msg", "Sender name is: $senderName")
                            }
                            .addOnFailureListener { e ->
                                Log.d("msg", "Error adding document", e)
                            }
                        messageBox.setText("")
                    }
                }
                .addOnFailureListener {exception ->
                    Log.w("nameQuery", "Error getting documents: ", exception)
                }

        }
    }
}