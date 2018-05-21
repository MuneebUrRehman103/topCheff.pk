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
import com.google.firebase.database.FirebaseDatabase
import okhttp3.*
import okhttp3.RequestBody
import java.text.SimpleDateFormat
import java.util.*


class CartMenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart_menu)


        // Task 1 : Create the adapter for the recycler view

             val database = Database(this)


            val listOfOrderInTheCart = database.getItemsAddedToCart()

            recyclerViewForCartMenu.layoutManager = LinearLayoutManager(this)
            recyclerViewForCartMenu.adapter = AdapterOfRecyclerViewForCartMenu(listOfOrderInTheCart)



            btnSubmitOrder.setOnClickListener {

                val formBody = FormBody.Builder()
                        .add("key","12jk123jk12kj3bn4h")
                        .add("receiver","muneeburrehman103@gmail.com")
                        .add("subject","dasdasd")
                        .add("message","dasdasd")
                        .build()

                showDialogBox()


                val urlOfApiToEmailOrders = "http://topchef.pk/api/sendMail.php"

                val JSON = MediaType.parse("application/json; charset=utf-8");

                val paramsOfEmailToBeSent = "{'key':'12jk123jk12kj3bn4h','receiver':'muneeburrehman103@gmail.com','subject':'android works test','message':'tofcheff oreder details'}"
                val bodyOfRequestForApi = RequestBody.create(JSON,paramsOfEmailToBeSent)

                val request = Request.Builder()
                        .url(urlOfApiToEmailOrders)
                        .post(formBody)
                        .addHeader("content-type","application/x-www-form-urlencoded")
                        .build()

                var client = OkHttpClient()

                client.newCall(request).enqueue(object : Callback{

                    override fun onResponse(call: Call?, response: Response?) {

                        val body = response?.body()?.string()
                        println(body)


                    }

                    override fun onFailure(call: Call?, e: IOException?) {
                    }


                })

            }




        // Task 2  : send the data to the firebase to be stored when the submit order button id pressed





    }





    fun showDialogBox(){

        val alert = AlertDialog.Builder(this)
        alert.setTitle("One Last Step To Place Your Order")
        alert.setMessage("Enter the required details :")
        alert.setIcon(R.drawable.add_to_cart_image)


        val txtBoxForAddress = EditText(this)
        txtBoxForAddress.hint = "Enter your address"

        val txtBoxForMessage= EditText(this)
        txtBoxForMessage.hint = "enter any details with respect to your order for our topcheff ."



        alert.setPositiveButton("Yes",DialogInterface.OnClickListener{dialog, which ->

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

            placeUsersOrderWithUserDetailsInFirebase("103","002","Malai Boti")




        })



        val dialog = alert.create()
        dialog.setView(txtBoxForAddress)
        dialog.setView(txtBoxForMessage)









        dialog.show()


    }






    fun placeUsersOrderWithUserDetailsInFirebase(userId : String , orderId : String ,orderTitle : String){

        val firebaseDatabaseRef = FirebaseDatabase.getInstance().getReference("orders")


        firebaseDatabaseRef.child(userId).child(orderId).child("order_name").setValue(orderTitle)


    }


}
