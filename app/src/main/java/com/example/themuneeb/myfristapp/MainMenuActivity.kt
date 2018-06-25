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

//            var intent = Intent(this@PhoneNoLoginActivity, RegisterUserActivity::class.java)
//
//
//            intent.putExtra("phoneNo", phoneNo)
//            intent.putExtra("sessionId", sessionIdForUserLogedIn)
//
//
//            startActivity(intent)

            /// catering daawat lunchbox single_pick beemar

            val selectedMenuOptionName = "buzurg"

            val intent = Intent(this,GeneralMenuForSelectedCategoryActivity::class.java)
            intent.putExtra("selectedMenuOptionName", selectedMenuOptionName)


            startActivity(intent)
        }

        cardViewForCateringMenu.setOnClickListener{


            val selectedMenuOptionName = "catering"

            val intent = Intent(this,GeneralMenuForSelectedCategoryActivity::class.java)
            intent.putExtra("selectedMenuOptionName", selectedMenuOptionName)


            startActivity(intent)

        }

        cardViewForDawatMenu.setOnClickListener{



            val selectedMenuOptionName = "daawat"

            val intent = Intent(this,GeneralMenuForSelectedCategoryActivity::class.java)
            intent.putExtra("selectedMenuOptionName", selectedMenuOptionName)


            startActivity(intent)

        }

        cardViewForLunchBoxesMenu.setOnClickListener {



            val selectedMenuOptionName = "lunchbox"

            val intent = Intent(this,GeneralMenuForSelectedCategoryActivity::class.java)
            intent.putExtra("selectedMenuOptionName", selectedMenuOptionName)


            startActivity(intent)

        }

        cardViewForWeeklyMonthlyPick.setOnClickListener {



//            val selectedMenuOptionName = "beemar"
//
//            val intent = Intent(this,GeneralMenuForSelectedCategoryActivity::class.java)
//            intent.putExtra("selectedMenuOptionName", selectedMenuOptionName)
//
//
//            startActivity(intent)

        }

        cardViewForBeemar.setOnClickListener {



            val selectedMenuOptionName = "Beemar_breakfast"

            val intent = Intent(this,GeneralMenuForSelectedCategoryActivity::class.java)
            intent.putExtra("selectedMenuOptionName", selectedMenuOptionName)


            startActivity(intent)

        }



    }





}
