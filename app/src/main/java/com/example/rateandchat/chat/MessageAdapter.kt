package com.example.rateandchat.chat

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rateandchat.R
import com.example.rateandchat.dataclass.Message
import com.google.firebase.auth.FirebaseAuth

class MessageAdapter(val context : Context, val messageList : ArrayList<Message>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    // Sets the two types of messages, sent and received, and switches the item layout basing on that.
    val ITEM_RECEIVED = 1
    val ITEM_SENT = 2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == 1) {
            // inflate receive
            val view : View = LayoutInflater.from(context).inflate(R.layout.received_message, parent, false)
            return ReceivedViewHolder(view)
        } else {
            // inflate sent
            val view : View = LayoutInflater.from(context).inflate(R.layout.sent_message, parent, false)
            return SentViewHolder(view)
        }
    }

    override fun getItemCount(): Int {
        return messageList.size
    }

    override fun getItemViewType(position: Int): Int {
        val currentMessage = messageList[position]

        // Compares user id with sender id and picks the right type.
        if(FirebaseAuth.getInstance().currentUser?.uid.equals(currentMessage.senderId)) {
            return ITEM_SENT
        } else {
            return ITEM_RECEIVED
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val currentMessage = messageList[position]

        if(holder.javaClass == SentViewHolder::class.java) {
            // do stuff for sent view holder
            val viewHolder = holder as SentViewHolder
            holder.sentMessage.text = currentMessage.message

        } else {
            // do stuff for received view holder
            val viewHolder = holder as ReceivedViewHolder
            holder.receivedMessage.text = currentMessage.message
            holder.senderName.text = currentMessage.senderName
        }
    }


    // Two different view holders, depending on whether the message is sent or received
    inner class SentViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val sentMessage = itemView.findViewById<TextView>(R.id.txt_sent_message)
    }

    inner class ReceivedViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val receivedMessage = itemView.findViewById<TextView>(R.id.txt_received_message)
        val senderName = itemView.findViewById<TextView>(R.id.senderNameText)
    }

}