package com.example.themuneeb.myfristapp

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



                showDialogBox()





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



            val database = Database(this)
            database.removeAllItemsFromCart()



            Toast.makeText(
                    this,
                    "Your Order Has Been Placed,You Will Be Contacted Shorlty ",
                    Toast.LENGTH_LONG
            ).show()

        })



        val dialog = alert.create()
        dialog.setView(txtBoxForAddress)
        dialog.setView(txtBoxForMessage)









        dialog.show()


    }

}
