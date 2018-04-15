package com.example.themuneeb.myfristapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle



import kotlinx.android.synthetic.main.activity_main_menu.*


class MainMenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        cardViewForBuzurgMenu.setOnClickListener {

            val selectedMenuOptionName = "BuzurgMenu"
            val intent = Intent(this,GeneralMenuForSelectedCategoryActivity::class.java)
            startActivity(intent)
        }

        cardViewForCateringMenu.setOnClickListener{

            val selectedMenuOptionName = "CateringMenu"
            val intent = Intent(this,GeneralMenuForSelectedCategoryActivity::class.java)
            startActivity(intent)

        }

        cardViewForDawatMenu.setOnClickListener{

            val selectedMenuOptionName = "DawatMenu"
            val intent = Intent(this,GeneralMenuForSelectedCategoryActivity::class.java)
            startActivity(intent)

        }

        cardViewForLunchBoxesMenu.setOnClickListener {

           // val selectedMenuOptionName = "LunchBoxMenu"
            val intent = Intent(this,GeneralMenuForSelectedCategoryActivity::class.java)
            startActivity(intent)

        }
    }





}
