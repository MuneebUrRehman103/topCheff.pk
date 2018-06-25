package com.example.themuneeb.myfristapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.themuneeb.myfristapp.Database.Database
import com.example.themuneeb.myfristapp.ViewHolder.AdapterForRecyclerViewOfPendingOrdeDetails
import com.example.themuneeb.myfristapp.ViewHolder.AdapterForRecyclerViewOfPendingOrders
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_pending_orders.*
import kotlinx.android.synthetic.main.activity_pending_orders_detail.*

class PendingOrdersDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pending_orders_detail)

        val orderId = intent.getStringExtra("orderId")


        addAdapterForRecyclerView(orderId)


        cardViewForChat.setOnClickListener{
            val withIntentToChangeToChatActivity = Intent(this,ChatActivity::class.java)
            withIntentToChangeToChatActivity.putExtra("orderId",orderId)
            startActivity(withIntentToChangeToChatActivity)
        }




    }


    fun getUserIdFromDatabase() : String {


        val database = Database(this)


        var userIdFromDatabase = database.getUserId()


        return userIdFromDatabase

    }


    fun addAdapterForRecyclerView(orderId : String) {

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



                val listOfOrderDetailsForGivenOrderNo = provideOrderDetailsForGivenOrderNoFromAllTheOrderDetailsGiven(orderId.toInt(),allOrderDetailsInMap)



                recViewForPendingOrdersDetail.layoutManager = LinearLayoutManager(this@PendingOrdersDetailActivity)
                recViewForPendingOrdersDetail.adapter = AdapterForRecyclerViewOfPendingOrdeDetails(listOfOrderDetailsForGivenOrderNo)

                firebaseInst.removeEventListener(this)



            }



        })


    }





}

fun provideOrderDetailsForGivenOrderNoFromAllTheOrderDetailsGiven ( orderNo : Int , allOrderDetails : MutableList<HashMap<Any, Any>?>) : MutableList<orderDetailOfUser> {



    var listOfOrderDetails = mutableListOf<orderDetailOfUser>()

    val orderDetailForOrderNo = allOrderDetails.get(orderNo)

    var itemDelivered = "false"

    for (key in orderDetailForOrderNo!!.keys) {

        var receivedKey = key as String

        if (receivedKey == "delivered") {

            itemDelivered = orderDetailForOrderNo[key] as String


        }else{

         val orderDetails = orderDetailForOrderNo[key] as HashMap<String, Any>

            val itemName : String = orderDetails["itemName"] as String
            val itemQuantity : String = orderDetails["quantity"] as String

            val orderDetailOfUser = orderDetailOfUser(itemName,itemQuantity,itemDelivered)


            listOfOrderDetails.add(orderDetailOfUser)

        }


    }

    return listOfOrderDetails

}


class orderDetailOfUser(var itemName:String,var quantity : String ,var delivered : String) {




}