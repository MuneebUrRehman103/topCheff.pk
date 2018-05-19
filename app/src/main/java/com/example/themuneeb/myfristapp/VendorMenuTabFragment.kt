package com.example.themuneeb.myfristapp


import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_standard_menu.*
import kotlinx.android.synthetic.main.fragment_standar_menu.view.*

/**
 * Created by TheMuneeb on 4/14/2018.
 */



class VendorMenuTabFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {




        val view: View
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        view = inflater.inflate(R.layout.fragment_standar_menu,container,false)
        view.recViewStandardMenu.layoutManager = LinearLayoutManager(activity)

      //  view.recViewStandardMenu.adapter = AdapterOfRecyclyerViewStandarMenu()

        return view
    }



}