package com.example.themuneeb.myfristapp

import com.example.themuneeb.myfristapp.ViewHolder.AdapterOfRecyclerViewForCartMenu
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_cart_menu.*



class CartMenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart_menu)


        // Task 1 : Create the adapter for the recycler view

            recyclerViewForCartMenu.layoutManager = LinearLayoutManager(this)
            recyclerViewForCartMenu.adapter = AdapterOfRecyclerViewForCartMenu()




        // Task 2  : send the data to the firebase to be stored when the submit order button id pressed





    }
}
