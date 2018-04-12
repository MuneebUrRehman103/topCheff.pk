package com.example.themuneeb.myfristapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_menu_details.*

class MenuDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_details)


        btnAddToCart.setOnClickListener{


            // Task 1 : call the insert method of database to insert items of cart in database




            Toast.makeText(this,"Added To Cart" , Toast.LENGTH_SHORT).show()


        }


    }
}
