package com.example.themuneeb.myfristapp

import android.content.Intent
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.example.themuneeb.myfristapp.ViewHolder.AdapterForGeneralMenuTabbedActvity

import kotlinx.android.synthetic.main.activity_general_menu_for_selected_category.*

class GeneralMenuForSelectedCategoryActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_general_menu_for_selected_category)


        var selectedMenuOptionName = intent.getStringExtra("selectedMenuOptionName")




        val adapterOfFragmentsForPagesOfTabsMenu = AdapterForGeneralMenuTabbedActvity(supportFragmentManager)


        val standardMenuFragment = StandarMenuFragment()

        val argsForStandardMenu = Bundle()
        argsForStandardMenu.putString("typeOfMenu","MenuItems")
        argsForStandardMenu.putString("categoryOfMenuSelected",selectedMenuOptionName.toString())

        standardMenuFragment.setArguments(argsForStandardMenu)

/////////////////////////////

        val vendorMenuFragment = StandarMenuFragment()

        val argsForVendorMenu = Bundle()
        argsForVendorMenu.putString("typeOfMenu","VendorItems")
        argsForStandardMenu.putString("categoryOfMenuSelected",selectedMenuOptionName.toString())

        vendorMenuFragment.setArguments(argsForVendorMenu)

/////////////////////////////////

        adapterOfFragmentsForPagesOfTabsMenu.addFragment(standardMenuFragment,"Standard Menu")
        adapterOfFragmentsForPagesOfTabsMenu.addFragment(vendorMenuFragment,"Vendor Menu")

        pager.adapter = adapterOfFragmentsForPagesOfTabsMenu


        tabs.setupWithViewPager(pager)


        // floating a button :: fab ::


        fab.setOnClickListener {


            val withIntentToChangeToCartMenuActivity = Intent(this,CartMenuActivity::class.java)
            startActivity(withIntentToChangeToCartMenuActivity)


        }



        btnToOpenChat.setOnClickListener {


            val withIntentToChangeToChatActivity = Intent(this,ChatActivity::class.java)
            startActivity(withIntentToChangeToChatActivity)


        }


    }











}
