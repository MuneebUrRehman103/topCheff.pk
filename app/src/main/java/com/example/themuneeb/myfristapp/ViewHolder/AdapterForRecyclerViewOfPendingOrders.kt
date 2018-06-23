package com.example.themuneeb.myfristapp.ViewHolder

import android.content.Intent
import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.themuneeb.myfristapp.CustomViewHolder

import com.example.themuneeb.myfristapp.MenuDetailsActivity
import com.example.themuneeb.myfristapp.PendingOrdersDetailActivity
import com.example.themuneeb.myfristapp.R


import kotlinx.android.synthetic.main.custom_view_for_rec_view_of_pending_orders.*
import kotlinx.android.synthetic.main.custom_view_for_rec_view_of_pending_orders.view.*

/**
 * Created by imac on 22/06/2018.
 */






class AdapterForRecyclerViewOfPendingOrders(val listOfPendingOrderNo : MutableList<Int>)  : RecyclerView.Adapter<CustomViewHolder>(){



    override fun getItemCount(): Int {


        return  listOfPendingOrderNo.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CustomViewHolder {



        val cardViewForPendingOrderNo =  LayoutInflater.from(parent?.context).inflate(R.layout.custom_view_for_rec_view_of_pending_orders,parent,false)


        cardViewForPendingOrderNo.setOnClickListener {

            val intent = Intent(parent?.context, PendingOrdersDetailActivity::class.java)

            ContextCompat.startActivity(parent?.context, intent, null)

        }




        return CustomViewHolder(cardViewForPendingOrderNo)


    }

    override fun onBindViewHolder(holder: CustomViewHolder?, position: Int) {


        val orderNoOfPendingOrder = listOfPendingOrderNo.get(position)

        holder?.view?.LinearLayout1?.LinearLayout2?.LinearLayout3?.txtItemName?.text = "Order No : "+orderNoOfPendingOrder.toString()


    }

}

