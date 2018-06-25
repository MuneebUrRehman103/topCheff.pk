package com.example.themuneeb.myfristapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
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
import kotlinx.android.synthetic.main.activity_menu_details.view.sideBar

import okhttp3.*
import java.io.IOException


class MenuDetailsActivity(menuId: String = "1") : AppCompatActivity() {

    init {

    }


    var itemHasNotBeenAdded = true

    var menuIdToFetchItsItems = menuId
    var totalPriceForMenu = "123455"
    var menuItemsFetchedFromApi: MenuItemDetail? = null
    var menuTitleFetched = "Unknown Title"
    var menuCategory = "Unknown Category"
    var menuForNumberOfPeople = "Unknown People"
    var menuItemQuantity = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_details)



        setupSideBarToggle()

        setUpSideBar()


        if(intent.getStringExtra("menuId") != null ){
            menuIdToFetchItsItems = intent.getStringExtra("menuId")
        }
        if(intent.getStringExtra("menuTitle") != null ){
            menuTitleFetched = intent.getStringExtra("menuTitle")
        }
        if(intent.getStringExtra("menuTitleNoOfPeople") != null ){
            menuForNumberOfPeople = intent.getStringExtra("menuTitleNoOfPeople")
        }
        if(intent.getStringExtra("menuCategory") != null ){
            menuCategory = intent.getStringExtra("menuCategory")
        }
        if(intent.getStringExtra("menuPrice") != null ){
            totalPriceForMenu = intent.getStringExtra("menuPrice")
        }






        ///////////////////////////////////


        addingListenerForElegantNumberButton()

        addingListenerForAddToCartButton()

        fetchDataForRecViewOfMenuDetail(menuIdToFetchItsItems)

        settingUpTheOptionsOfSidebarsNavView()

        ///////////////////////////////////



        collapsingToolbarLayoutForMenuDetail.title = menuTitleFetched

    }


    fun addingListenerForElegantNumberButton() {


        btnElegantNumber.setOnValueChangeListener { myView, oldValue, newValue ->

         //   val num = btnElegantNumber.number

            menuItemQuantity = newValue

            txtPriceForMenuItemForMenuDetails.text = (totalPriceForMenu.toInt() * newValue.toInt()).toString() + " Rs"

          //  Toast.makeText(this, "Already Added To Cart: " + newValue, Toast.LENGTH_SHORT).show()

        }

//        btnElegantNumber.setOnClickListener(View.OnClickListener {
//
//            val num = btnElegantNumber.number
//
//            Toast.makeText(this, "Already Added To Cart: " + num, Toast.LENGTH_SHORT).show()
//
//        })

    }


    fun addingListenerForAddToCartButton() {


        btnAddToCart.setOnClickListener {


            if (itemHasNotBeenAdded) {

                // Task 1 : call the insert method of database to insert items of cart in database

                val database = Database(this)



                val productId = menuIdToFetchItsItems
                val productName = menuTitleFetched
                val quantity = menuItemQuantity.toString()
                val price = txtPriceForMenuItemForMenuDetails.text.toString()
                val discount = "0"

                val order = Order(productId, productName, quantity, price, discount)

                database.addItemsToCart(order)


                Toast.makeText(this, "Added To Cart", Toast.LENGTH_SHORT).show()


            } else {


                Toast.makeText(this, "Already Added To Cart", Toast.LENGTH_SHORT).show()


            }

            this.itemHasNotBeenAdded = false

        }


    }


    fun fetchDataForRecViewOfMenuDetail(menuidToFetchItsItems: String) {


        recViewForMenuDetail.layoutManager = LinearLayoutManager(this)


        ///////////////////////////////////////


        val urlToGetMenuForSpecificCaterers = "http://topchef.pk/api/router.php?method=getMenusDetails&menu=" + menuidToFetchItsItems

        val request = Request.Builder()
                .url(urlToGetMenuForSpecificCaterers)
                .build()

        var client = OkHttpClient()



        client.newCall(request).enqueue(object : Callback {

            override fun onResponse(call: Call?, response: Response?) {

                val body = response?.body()?.string()
                println(body)

                val gson = GsonBuilder().create()

                var menuItemDetails = gson.fromJson(body, MenuItemDetail::class.java)

                menuItemsFetchedFromApi = menuItemDetails


                runOnUiThread {


                    recViewForMenuDetail.adapter = AdapterForMenuDetailsActivity(menuItemDetails)

                    txtMenuPersonDetail.text = menuForNumberOfPeople + " PERSONS"
                    txtMenuType.text = "type : " + menuCategory
                    txtPriceForMenuItemForMenuDetails.text = totalPriceForMenu + " Rs"

                }

                println(menuItemDetails)


            }

            override fun onFailure(call: Call?, e: IOException?) {
            }


        })


    }


    fun settingUpTheOptionsOfSidebarsNavView() {

        navView.setNavigationItemSelectedListener { menuItem ->

            val itemId = menuItem.itemId

            if (menuItem.toString() == "Profile") {
                // profile

                Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show()

            }
            if (menuItem.toString() == "My Order") {
                // myOrders

                Toast.makeText(this, "My orders", Toast.LENGTH_SHORT).show()

            }
            if (menuItem.toString() == "Chat") {
                // Chat


                val withIntentToChangeToChatActivity = Intent(this, ChatActivity::class.java)
                startActivity(withIntentToChangeToChatActivity)


                //  Toast.makeText(this, "Chat" , Toast.LENGTH_SHORT).show()

            }
            if (menuItem.toString() == "Logout") {
                // Logout

                Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show()

            }



            true
        }

    }


    fun setupSideBarToggle(){

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val drawerToggle = ActionBarDrawerToggle(this,sideBar,toolBarForMenuDetails,R.string.drawer_open,R.string.drawer_close)
        drawerToggle.isDrawerIndicatorEnabled = true
        drawerToggle.syncState()


        sideBar.addDrawerListener(drawerToggle)


    }


    fun setUpSideBar(){


        navView.setNavigationItemSelectedListener {

            when (it.itemId) {
                R.id.menuProfile -> {
                    // handle click




                    val intent = Intent(this, RegisterUserActivity::class.java)
                    intent.putExtra("isClickedFromSideBar","true")


                    startActivity(intent)


                    true
                }

                R.id.menuChat -> {
                    // handle click




                    val intent = Intent(this, PendingOrdersActivity::class.java)
                    intent.putExtra("isClickedFromSideBar","true")


                    startActivity(intent)


                    true
                }
                R.id.menuMyOrder -> {
                    // handle click



                    val intent = Intent(this, PendingOrdersActivity::class.java)
                    intent.putExtra("isClickedFromSideBar","true")


                    startActivity(intent)


                    true
                }
                R.id.menuLogout -> {
                    // handle click



                    val intent = Intent(this, PhoneNoLoginActivity::class.java)

                    startActivity(intent)




                    true
                }

                else -> false
            }


        }




    }



}
