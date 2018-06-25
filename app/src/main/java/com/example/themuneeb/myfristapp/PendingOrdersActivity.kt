package com.example.themuneeb.myfristapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.themuneeb.myfristapp.Database.Database
import com.example.themuneeb.myfristapp.ViewHolder.AdapterForRecyclerViewOfPendingOrders
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_pending_orders.*

class PendingOrdersActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pending_orders)
    //    fetchAllThePendingOrdersFromFirebase()

        addAdapterForRecyclerView()


    }

    fun getUserIdFromDatabase() : String {


        val database = Database(this)


        var userIdFromDatabase = database.getUserId()


        return userIdFromDatabase

    }


//    fun fetchAllThePendingOrdersFromFirebase() : MutableList<Int> {
//
//
//        var userIdFromDatabase = getUserIdFromDatabase()
//
//
//        val firebaseInst = FirebaseDatabase.getInstance().getReference("orders")
//
//
//        firebaseInst.child(userIdFromDatabase).child("unwantedKey").setValue("786")
//
//        firebaseInst.addValueEventListener(object : ValueEventListener {
//
//            override fun onCancelled(p0: DatabaseError?) {
//            }
//
//            override fun onDataChange(dataSnapshot: DataSnapshot?) {
//
//
//                val valueReceivedFromFirebase = dataSnapshot?.value as HashMap<String, Any>
//
//                val orderResponseReceivedFromFirebase = valueReceivedFromFirebase[userIdFromDatabase] as HashMap<String, Any>
//
//                val maxOrderNoOfUsersInFirebase = orderResponseReceivedFromFirebase["latest_order_no"].toString()
//
//
//
//                for (orderNumberOfUser in 0..maxOrderNoOfUsersInFirebase.toInt()) {
//
//                    val orderDetailsForOrderNumber = orderResponseReceivedFromFirebase[orderNumberOfUser.toString()] as HashMap<String, Any>
//
//                    if (orderDetailsForOrderNumber != null) {
//
//
//                        allOrderNo.add(orderNumberOfUser)
//
//
//                    }
//
//
//                }
//
//                firebaseInst.removeEventListener(this)
//
//
//
//            }
//
//
//
//        })
//
//        return allOrderNo
//    }


    fun addAdapterForRecyclerView() {

//        val listOfPendingOrderNo = fetchAllThePendingOrdersFromFirebase()




        val allOrderNo = mutableListOf<Int>()

        var userIdFromDatabase = getUserIdFromDatabase()

        var orderDetailsForOrderNumber : HashMap<Any, Any>?

        var allOrderDetailsInMap = mutableListOf<HashMap<Any, Any>?>()

        val firebaseInst = FirebaseDatabase.getInstance().getReference("orders")


        firebaseInst.child(userIdFromDatabase).child("unwantedKey").setValue("786")

        firebaseInst.addValueEventListener(object : ValueEventListener {

            override fun onCancelled(p0: DatabaseError?) {
            }

            override fun onDataChange(dataSnapshot: DataSnapshot?) {


                val valueReceivedFromFirebase = dataSnapshot?.value as HashMap<String, Any>

                val orderResponseReceivedFromFirebase = valueReceivedFromFirebase["03122685832"] as HashMap<String, Any>

                val maxOrderNoOfUsersInFirebase = orderResponseReceivedFromFirebase["latest_order_no"].toString()



                for (orderNumberOfUser in 0..maxOrderNoOfUsersInFirebase.toInt()) {

                    if (orderNumberOfUser<=maxOrderNoOfUsersInFirebase.toInt()){

                    orderDetailsForOrderNumber = orderResponseReceivedFromFirebase[orderNumberOfUser.toString()]  as HashMap<Any, Any>?

                    if (orderDetailsForOrderNumber != null) {


                        allOrderNo.add(orderNumberOfUser.toInt())

                        allOrderDetailsInMap.add(orderDetailsForOrderNumber)


                    }
                }


                }

                recViewForPendingOrders.layoutManager = LinearLayoutManager(this@PendingOrdersActivity)
                recViewForPendingOrders.adapter = AdapterForRecyclerViewOfPendingOrders(allOrderNo)

                firebaseInst.removeEventListener(this)



            }



        })











    }



}
