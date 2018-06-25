package com.example.themuneeb.myfristapp.ViewHolder

import android.content.Intent
import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.themuneeb.myfristapp.*
import kotlinx.android.synthetic.main.custom_view_for_rec_view_of_pending_order_details.*
import kotlinx.android.synthetic.main.custom_view_for_rec_view_of_pending_order_details.view.*


/**
 * Created by imac on 22/06/2018.
 */




class AdapterForRecyclerViewOfPendingOrdeDetails(val listOfOrderDetails : MutableList<orderDetailOfUser>)  : RecyclerView.Adapter<CustomViewHolder>(){



    override fun getItemCount(): Int {


        return  listOfOrderDetails.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CustomViewHolder {



        val cardViewForPendingOrderDetails =  LayoutInflater.from(parent?.context).inflate(R.layout.custom_view_for_rec_view_of_pending_order_details,parent,false)




        cardViewForPendingOrderDetails.setOnClickListener {
//            val withIntentToChangeToChatActivity = Intent(this, ChatActivity::class.java)
//            startActivity(withIntentToChangeToChatActivity)
        }




        return CustomViewHolder(cardViewForPendingOrderDetails)


    }

    override fun onBindViewHolder(holder: CustomViewHolder?, position: Int) {


            val orderDetails = listOfOrderDetails.get(position)


            holder?.view?.LinearLayout1?.LinearLayout2?.LinearLayout3?.txtItemName?.text = orderDetails.itemName

            holder?.view?.LinearLayout1?.LinearLayout2?.LinearLayout3?.txtQuantity?.text = "Quantity : "+orderDetails.quantity

    }

}

