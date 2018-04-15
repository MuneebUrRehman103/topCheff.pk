package com.example.themuneeb.myfristapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.themuneeb.myfristapp.Database.Database
import com.example.themuneeb.myfristapp.Model.Order
import kotlinx.android.synthetic.main.activity_menu_details.*
import kotlinx.android.synthetic.main.activity_menu_details.view.*

class MenuDetailsActivity : AppCompatActivity() {

    var itemHasNotBeenAdded = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_details)

      btnAddToCart.setOnClickListener{


            if (itemHasNotBeenAdded) {

                // Task 1 : call the insert method of database to insert items of cart in database

                val database = Database(this)

                // dummy order :: when api will be set then menu view's data stored in this class will be used


                val productId = "12345"
                val productName = "PizzaHutDeal-No-1"
                val quantity = "1"
                val price = "1000"
                val discount = "0"

                val order = Order(productId,productName,quantity,price,discount)

                database.addItemsToCart(order)


                Toast.makeText(this, "Added To Cart", Toast.LENGTH_SHORT).show()




            }
            else{


                Toast.makeText(this, "Already Added To Cart", Toast.LENGTH_SHORT).show()


            }

          this.itemHasNotBeenAdded = false

        }



        layoutOfMenuDetailsItemsList.cardView1.setOnClickListener{



        }


}

}
