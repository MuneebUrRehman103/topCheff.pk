package com.example.themuneeb.myfristapp.ViewHolder

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.themuneeb.myfristapp.CustomViewHolder
import kotlinx.android.synthetic.main.custom_view_of_rec_view_for_cart_menu.view.*

/**
 * Created by TheMuneeb on 4/8/2018.
 */





import android.support.v7.widget.CardView
import android.view.View
import android.widget.TextView
import com.example.themuneeb.myfristapp.R
import kotlinx.android.synthetic.main.custom_view_of_rec_view_for_cart_menu.*
import kotlinx.android.synthetic.main.custom_view_of_rec_view_for_cart_menu.view.*

/**
 * Created by TheMuneeb on 3/25/2018.
 */



class AdapterOfRecyclerViewForCartMenu : RecyclerView.Adapter<CustomViewHolder>(){


    var dummyItemsNames = listOf("Al-Habib Pakwan deal No : 3","Al-Meezan deal No : 6", "Pizza Hut deal No :1")
    var dummyItemsPrice = listOf("1000","1250","2520")


    override fun getItemCount(): Int {
        return dummyItemsNames.size

    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CustomViewHolder {

        val layoutInflator = LayoutInflater.from(parent?.context)


        val cellForRow =  layoutInflator.inflate(R.layout.custom_view_of_rec_view_for_cart_menu,parent,false)

        return CustomViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolder?, position: Int) {


        val nameOfItemForCartMenu = dummyItemsNames.get(position)
//        val priceOfItemForCartMenu = dummyItemsPrice.get(position)


        holder?.view?.LinearLayout1?.txtItemName?.text = nameOfItemForCartMenu

        holder?.view?.LinearLayout1?.txtPrice?.text = nameOfItemForCartMenu




      }

}



