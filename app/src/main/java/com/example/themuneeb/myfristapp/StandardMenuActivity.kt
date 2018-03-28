package com.example.themuneeb.myfristapp

import android.graphics.Color
import android.media.Image
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.CardView
import android.support.v7.widget.LinearLayoutManager
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout.LayoutParams
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_standard_menu.*

class StandardMenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_standard_menu)


//        recViewStandardMenu.setBackgroundColor(Color.GREEN)
       recViewStandardMenu.layoutManager = LinearLayoutManager(this)
        recViewStandardMenu.adapter = AdapterOfRecyclyerViewStandarMenu()




    }



}
