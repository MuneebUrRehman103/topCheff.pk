package com.example.themuneeb.myfristapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.themuneeb.myfristapp.ViewHolder.AdapterForGeneralMenuTabbedActvity
import kotlinx.android.synthetic.main.activity_general_menu_for_selected_category.*

class SelectedVendorsMenuActivity : AppCompatActivity() {

    var SelectedVendorTitle = "Desi Tarka"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_general_menu_for_selected_category)


        val adapterOfFragmentsForPagesOfTabsMenu = AdapterForGeneralMenuTabbedActvity(supportFragmentManager)

/////////////////////////////

        val standardMenuFragment = StandarMenuFragment()

        val argsForStandardMenu = Bundle()
        argsForStandardMenu.putString("typeOfMenu","MenuItems")

        standardMenuFragment.arguments = argsForStandardMenu

////////////////////////////////

        adapterOfFragmentsForPagesOfTabsMenu.addFragment(standardMenuFragment,SelectedVendorTitle)

        pager.adapter = adapterOfFragmentsForPagesOfTabsMenu


        tabs.setupWithViewPager(pager)


        // floating a button :: fab ::


        fab.setOnClickListener {


            val withIntentToChangeToCartMenuActivity = Intent(this,CartMenuActivity::class.java)
            startActivity(withIntentToChangeToCartMenuActivity)


        }

    }

}
