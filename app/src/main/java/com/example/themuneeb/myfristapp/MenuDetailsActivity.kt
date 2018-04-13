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
                val order = Order()

                order.productId = "12345"
                order.productName = "PizzaHutDeal-No-1"
                order.quantity = "1"
                order.price = "1000"
                order.discount = "0"

                database.addItemsToCart(order)


                Toast.makeText(this, "Added To Cart", Toast.LENGTH_SHORT).show()




            }
            else{


                Toast.makeText(this, "Already Added To Cart", Toast.LENGTH_SHORT).show()


            }

          this.itemHasNotBeenAdded = false

        }



        layoutOfMenuDetailsItemsList.cardView1.setOnClickListener{

            val withIntentToChangeToCartMenuActivity = Intent(this,CartMenuActivity::class.java)
            startActivity(withIntentToChangeToCartMenuActivity)


        }


}

}
