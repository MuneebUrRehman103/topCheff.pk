package com.example.themuneeb.myfristapp

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.themuneeb.myfristapp.Database.Database
import com.example.themuneeb.myfristapp.Model.Message
import com.example.themuneeb.myfristapp.Model.User
import com.example.themuneeb.myfristapp.ViewHolder.AdapterForRecyclerViewOfChat
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_chat.*
import kotlinx.android.synthetic.main.activity_standard_menu.*
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*
import kotlin.collections.HashMap


class ChatActivity : AppCompatActivity() {





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        val orderId = intent.getStringExtra("orderId")


        recViewToViewAllMessages.layoutManager = LinearLayoutManager(this)


       var messages = mutableListOf<Message>()
        val message1 = Message("Admin","Your Order is being prepared :) if you have any queries please ask below :","000")
        val message2 = Message("Admin","Your Order is being prepared :) if you have any queries please ask below :","000")

        messages.add(message1)
       // messages.add(message2)

        val adapter = AdapterForRecyclerViewOfChat(messages)


        recViewToViewAllMessages.adapter = adapter


        val userDetails = getUserDetailFromDatabase()

                                                                                                                        //userPhoneNumber = Userid//
        val firebaseDatabaseReference = FirebaseDatabase.getInstance().getReference("chatOfOrdersByUsers").child(userDetails.phoneno).child(orderId)

        firebaseDatabaseReference.addChildEventListener( object : ChildEventListener {
            override fun onCancelled(p0: DatabaseError?) {

            }

            override fun onChildMoved(p0: DataSnapshot?, p1: String?) {

            }

            override fun onChildChanged(p0: DataSnapshot?, p1: String?) {
            }

            override fun onChildAdded(dataSnapshot: DataSnapshot?, p1: String?) {


                val time = dataSnapshot?.key
                val msg = dataSnapshot?.value as HashMap<String,String>


                val message = Message(msg["sender"].toString(),msg["text"].toString(),time.toString())

                messages.add(message)
                adapter.notifyDataSetChanged()

                recViewToViewAllMessages.scrollToPosition(messages.count() - 1)

            }

            override fun onChildRemoved(p0: DataSnapshot?) {
            }



        })





        btnSendMessage.setOnClickListener {

            var dateFormat = SimpleDateFormat("dd:M:yyyy;hh:mm:ss")
            var timeStamp = dateFormat.format(Date())



           var messageByUser =  txtMessageByUser.text.toString()


            val time = timeStamp.toString()


            val userDetails = getUserDetailFromDatabase()

                                            //userPhoneNumber = UserId//
            addUserMessageByUserIdAndOrderId(userDetails.phoneno,userDetails.username,orderId,time,txtMessageByUser.text.toString())

            txtMessageByUser.setText("")

         }






    }




//    firebaseDatabaseReference.addValueEventListener(object : ValueEventListener {
//        override fun onDataChange(dataSnapshot: DataSnapshot) {
//            // This method is called once with the initial value and again
//            // whenever data at this location is updated.
//            val value = dataSnapshot.getValue(String::class.java)
//
//        }
//
//        override fun onCancelled(error: DatabaseError) {
//            // Failed to read value
//        }
//    })



    fun getUserDetailFromDatabase(): User {

        val databaseRef = Database(this)

        val allUserDetails = databaseRef.getUserRegisterDetail()

        val userDetails = allUserDetails[0]

        return userDetails
    }


    fun addUserMessageByUserIdAndOrderId (userId : String , username : String , orderId : String , timeOfMessage : String , message : String){


        val firebaseDatabaseReference1 = FirebaseDatabase.getInstance().getReference("chatOfOrdersByUsers")

        val msg : HashMap<String,String> = HashMap<String,String>()

        msg.put("text",message)
        msg.put("sender",username)

        firebaseDatabaseReference1.child(userId).child(orderId).child(timeOfMessage).setValue(msg)



    }



}
