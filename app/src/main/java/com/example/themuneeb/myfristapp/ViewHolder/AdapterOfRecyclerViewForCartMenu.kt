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
import com.example.themuneeb.myfristapp.Model.Order
import com.example.themuneeb.myfristapp.R
import kotlinx.android.synthetic.main.custom_view_of_rec_view_for_cart_menu.*
import kotlinx.android.synthetic.main.custom_view_of_rec_view_for_cart_menu.view.*

/**
 * Created by TheMuneeb on 3/25/2018.
 */



class AdapterOfRecyclerViewForCartMenu(listOfOrders : List<Order?>) : RecyclerView.Adapter<CustomViewHolder>(){

//
//    var dummyItemsNames = listOf("Al-Habib Pakwan deal No : 3","Al-Meezan deal No : 6", "Pizza Hut deal No :1")
//    var dummyItemsPrice = listOf("RS : 1000","RS : 1250","RS : 2520")


    val listOfAllAddedOrders = listOfOrders

    override fun getItemCount(): Int {
        return listOfAllAddedOrders.size

    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CustomViewHolder {

        val layoutInflator = LayoutInflater.from(parent?.context)


        val cellForRow =  layoutInflator.inflate(R.layout.custom_view_of_rec_view_for_cart_menu,parent,false)

        return CustomViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolder?, position: Int) {

        val cartItemsId : MutableList<String?>  = arrayListOf()
        val cartItemsNames : MutableList<String?>  = arrayListOf()
        val cartItemsQuantity : MutableList<String?>  = arrayListOf()
        val cartItemsPrice : MutableList<String?>  = arrayListOf()
        val cartItemsDiscount : MutableList<String?>  = arrayListOf()

        for( order in listOfAllAddedOrders ){

            cartItemsId.add(order?.productId)
            cartItemsNames.add(order?.productName)
            cartItemsQuantity.add(order?.quantity)
            cartItemsPrice.add(order?.price)
            cartItemsDiscount.add(order?.discount)

        }





        holder?.view?.LinearLayout1?.txtItemName?.text = cartItemsNames.get(position)

        holder?.view?.LinearLayout1?.txtPrice?.text = cartItemsPrice.get(position)




      }

}



