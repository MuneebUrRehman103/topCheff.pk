package com.example.themuneeb.myfristapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.example.themuneeb.myfristapp.Database.Database
import com.example.themuneeb.myfristapp.Model.MenuItem
import com.example.themuneeb.myfristapp.Model.MenuItemDetail
import com.example.themuneeb.myfristapp.Model.Order
import com.example.themuneeb.myfristapp.ViewHolder.AdapterForMenuDetailsActivity
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_menu_details.*
import kotlinx.android.synthetic.main.activity_menu_details.view.*
import kotlinx.android.synthetic.main.fragment_standar_menu.view.*
import okhttp3.*
import java.io.IOException


class MenuDetailsActivity(menuId : String = "1") : AppCompatActivity() {

     init {

    }


    var itemHasNotBeenAdded = true



    var menuIdToFetchItsItems = menuId
    var totalPriceForMenu = 1500

    var menuItemsFetchedFromApi : MenuItemDetail? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_details)


        // get api data for standard menu details from :: http://topchef.pk/api/router.php?method=getMenusByVendor&vendor=2





        btnElegantNumber.setOnClickListener(View.OnClickListener {

            val num =  btnElegantNumber.number

            Toast.makeText(this, "Already Added To Cart: " +num, Toast.LENGTH_SHORT).show()

        })





        btnAddToCart.setOnClickListener{


            if (itemHasNotBeenAdded) {

                // Task 1 : call the insert method of database to insert items of cart in database

                val database = Database(this)

                // dummy order :: when api will be set then menu view's data stored in this class will be used


                val productId = "12345"
                val productName = "PizzaHutDeal-No-" + menuIdToFetchItsItems
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


        recViewForMenuDetail.layoutManager = LinearLayoutManager(this)



        ///////////////////////////////////////




        val urlToGetMenuForSpecificCaterers = "http://topchef.pk/api/router.php?method=getMenusDetails&menu="+menuIdToFetchItsItems

        val request = Request.Builder()
                .url(urlToGetMenuForSpecificCaterers)
                .build()

        var client = OkHttpClient()



        client.newCall(request).enqueue(object : Callback {

            override fun onResponse(call: Call?, response: Response?) {

                val body = response?.body()?.string()
                println(body)

                val gson = GsonBuilder().create()

                var  menuItemDetails = gson.fromJson(body, MenuItemDetail::class.java)

                menuItemsFetchedFromApi = menuItemDetails


                runOnUiThread {


                    recViewForMenuDetail.adapter = AdapterForMenuDetailsActivity(menuItemDetails)


                    txtPriceForMenuItemForMenuDetails.text = totalPriceForMenu.toString()

                }

                println(menuItemDetails)


            }

            override fun onFailure(call: Call?, e: IOException?) {
            }


        })







        //////////////////////////////////////////



    }

}
