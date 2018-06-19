package com.example.themuneeb.myfristapp


import java.io.IOException


import android.content.DialogInterface
import com.example.themuneeb.myfristapp.ViewHolder.AdapterOfRecyclerViewForCartMenu
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import com.example.themuneeb.myfristapp.Database.Database
import kotlinx.android.synthetic.main.activity_cart_menu.*
import android.os.AsyncTask.execute
import com.example.themuneeb.myfristapp.Model.Order
import com.example.themuneeb.myfristapp.Model.User
import com.google.firebase.database.FirebaseDatabase
import okhttp3.*
import okhttp3.RequestBody
import java.text.SimpleDateFormat
import java.util.*


class CartMenuActivity : AppCompatActivity() {


//        var productName : String = name
//        var productId : String = id
//        var quantity : String =  quantity
//        var price : String = price
//        var discount : String = discount


    var userId = "103"

    var orderId = "002"
    var orderTitle = "Chicken Tikkka"
    var listOfOrderInTheCart = listOf<Order>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart_menu)


        val database = Database(this)


        listOfOrderInTheCart = database.getItemsAddedToCart()


        passOrdersFromDatabaseToRecyclerView()





        btnSubmitOrder.setOnClickListener {


            showDialogBox()

            prepareAndSendEmail()

            for (order in listOfOrderInTheCart) {

                placeUsersOrderWithUserDetailsInFirebase(userId, order.productId, order.productName)

            }


        }


        // Task 2  : send the data to the firebase to be stored when the submit order button id pressed


    }


    fun prepareAndSendEmail() {


        var newline = "\n"

        var space = "\t"

        var emailStartHeading = "Hello Topchef, Here is your new Order :"

        var emailUserDetailHeading = "Topchef Customer Details :"

        // get user details  from database

        var databaseInstance = Database(this)

        var allUsersRegisteredFromDatabase  = listOf<User>()

        allUsersRegisteredFromDatabase = databaseInstance.getUserRegisterDetail()


        var userInfo = allUsersRegisteredFromDatabase[0]


        var emailUserId = "Order Sender's Id : " + userInfo.phoneno
        var emailUserName = "Order Sender's Name : "  + userInfo.username

        var emailUserAddress = "Order Sender's Address : " + userInfo.address

        var emailUserPhoneNo = "Order Sender's Phone no : "  + userInfo.phoneno

        var emailUserEmailAddress = "Order Sender's Email no : "  + userInfo.email

        var emailOrderStartingHeading = "Orders Of The Customers Is As Follows :"


        var emailEndHeading = "Please Make Sure That You Make This Order The Best Ever :)"


        // start

        var email = newline + space + emailStartHeading + newline + newline

        // user

        email += space + space + emailUserDetailHeading + newline + newline + newline +

                space + space + space + emailUserId + newline + newline +
                space + space + space + emailUserName + newline + newline +
                space + space + space + emailUserAddress + newline + newline +
                space + space + space + emailUserPhoneNo + newline + newline +
                space + space + space + emailUserEmailAddress + newline + newline + newline +

                space + space + emailOrderStartingHeading


        // order

        var orderNo = 1

        for (order in listOfOrderInTheCart) {


            ////


            var emailOrderNo = "Order no : " + orderNo

            var emailOrderId = "Order Id # : " + order.productId

            var emailOrderTitle = "Order Title : " + order.productName

            var emailOrderPrice = "Order Price : " + order.price

            var emailOrderDiscount = "Order Discount : " + order.discount

            var emailOrderQuantity = "Order Quantity : " + order.quantity

///

            email += newline + newline + newline +
                    space + space + emailOrderNo + newline + newline +
                    space + space + space + emailOrderId + newline + newline +
                    space + space + space + emailOrderTitle + newline + newline +
                    space + space + space + emailOrderPrice + newline + newline +
                    space + space + space + emailOrderDiscount + newline + newline +
                    space + space + space + emailOrderQuantity + newline + newline +


                    orderNo++
        }


        //end

        email += newline + newline + newline + space + space + emailEndHeading



        sendEmailForDetailsOfOrders(email)


    }

    fun passOrdersFromDatabaseToRecyclerView() {


        recyclerViewForCartMenu.layoutManager = LinearLayoutManager(this)
        recyclerViewForCartMenu.adapter = AdapterOfRecyclerViewForCartMenu(listOfOrderInTheCart)


    }


    fun sendEmailForDetailsOfOrders(email: String) {


        val formBody = FormBody.Builder()
                .add("key", "12jk123jk12kj3bn4h")
                .add("receiver", "muneeburrehman103@gmail.com")
                .add("subject", "TOP Chef Here Is Your New Order !")
                .add("message", email)
                .build()

        val urlOfApiToEmailOrders = "http://topchef.pk/api/sendMail.php"

        val JSON = MediaType.parse("application/json; charset=utf-8");

        val paramsOfEmailToBeSent = "{'key':'12jk123jk12kj3bn4h','receiver':'muneeburrehman103@gmail.com','subject':'android works test','message':'tofcheff oreder details'}"
        val bodyOfRequestForApi = RequestBody.create(JSON, paramsOfEmailToBeSent)

        val request = Request.Builder()
                .url(urlOfApiToEmailOrders)
                .post(formBody)
                .addHeader("content-type", "application/x-www-form-urlencoded")
                .build()

        var client = OkHttpClient()

        client.newCall(request).enqueue(object : Callback {

            override fun onResponse(call: Call?, response: Response?) {

                val body = response?.body()?.string()
                println(body)


            }

            override fun onFailure(call: Call?, e: IOException?) {
            }
/////

        })
    }


    fun showDialogBox() {

        val alert = AlertDialog.Builder(this)
        alert.setTitle("One Last Step To Place Your Order")
        alert.setMessage("Enter the required details :")
        alert.setIcon(R.drawable.add_to_cart_image)


        val txtBoxForAddress = EditText(this)
        txtBoxForAddress.hint = "Enter your address"

        val txtBoxForMessage = EditText(this)
        txtBoxForMessage.hint = "enter any details with respect to your order for our topcheff ."



        alert.setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, which ->

            val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
            val currentDate = sdf.format(Date())


            //////////////////////////////////////////////////////////////////////


            //  val addressForTheOrder = txtBoxForAddress.text.toString()

//                val urlOfApiToEmailOrders = "http://topchef.pk/api/sendMail.php"
//
//                val JSON = MediaType.parse("application/json;charset=urf-8")
//                val paramsOfEmailToBeSent = "{'key':'12jk123jk12kj3bn4h','receiver':'muneeburrehman103@gmail.com','subject':'android works test','message':'tofcheff oreder details'}"
//                val bodyOfRequestForApi = RequestBody.create(JSON,paramsOfEmailToBeSent)
//
//                val request = Request.Builder()
//                        .url(urlOfApiToEmailOrders)
//                        .post(bodyOfRequestForApi)
//                        .build()
//
//                val client = OkHttpClient()
//
//               val response = client.newCall(request).execute()
//
//                val orderSentResponseText = response.body().toString()
//
//                print(orderSentResponseText)

            //////////////////////////////////////////////////////////////////////

            Toast.makeText(
                    this,
                    "Success : Your Order Has Been Placed,You Will Be Contacted Shortly ",
                    Toast.LENGTH_LONG
            ).show()


            val database = Database(this)
            database.removeAllItemsFromCart()


        })


        val dialog = alert.create()
        dialog.setView(txtBoxForAddress)
        dialog.setView(txtBoxForMessage)









        dialog.show()


    }


    fun placeUsersOrderWithUserDetailsInFirebase(userId: String, orderId: String, orderTitle: String) {

        val firebaseDatabaseRef = FirebaseDatabase.getInstance().getReference("orders")


        firebaseDatabaseRef.child(userId).child(orderId).child("order_name").setValue(orderTitle)


    }


}
