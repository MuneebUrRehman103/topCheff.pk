package com.example.themuneeb.myfristapp

import android.content.Intent
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.os.Bundle
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


        val adapterOfFragmentsForPagesOfTabsMenu = AdapterForGeneralMenuTabbedActvity(supportFragmentManager)


        adapterOfFragmentsForPagesOfTabsMenu.addFragment(StandarMenuFragment(),"Standard Menu")
        adapterOfFragmentsForPagesOfTabsMenu.addFragment(StandarMenuFragment(),"Vendor Menu")

        pager.adapter = adapterOfFragmentsForPagesOfTabsMenu


        tabs.setupWithViewPager(pager)


        fab.setOnClickListener {


            val withIntentToChangeToCartMenuActivity = Intent(this,CartMenuActivity::class.java)
            startActivity(withIntentToChangeToCartMenuActivity)


        }

    }

}
