//package com.example.themuneeb.myfristapp.ViewHolder
//
//import android.content.Intent
//import android.graphics.Color
//import android.support.v4.content.ContextCompat
//import android.support.v7.widget.RecyclerView
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import com.example.themuneeb.myfristapp.CustomViewHolder
//import com.example.themuneeb.myfristapp.MenuDetailsActivity
//import com.example.themuneeb.myfristapp.R
//import kotlinx.android.synthetic.main.custom_view_for_rec_view_of_pending_order_details.*
//import kotlinx.android.synthetic.main.custom_view_for_rec_view_of_pending_order_details.view.*
//
//
///**
// * Created by imac on 22/06/2018.
// */
//
//
//
//
//class AdapterForRecyclerViewOfPendingOrdeDetails(val allOrderDetailsInMap : HashMap<String, Any>)  : RecyclerView.Adapter<CustomViewHolder>(){
//
//
//
//    override fun getItemCount(): Int {
//
//
//        return  allOrderDetailsInMap.count() - 1
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CustomViewHolder {
//
//
//
//        val cardViewForPendingOrderDetails =  LayoutInflater.from(parent?.context).inflate(R.layout.custom_view_for_rec_view_of_pending_order_details,parent,false)
//
//
//
//
//        cardViewForPendingOrderDetails.setOnClickListener {
//            //
////            val intent = Intent(parent?.context, MenuDetailsActivity::class.java)
////
////            ContextCompat.startActivity(parent?.context, intent, null)
//
//        }
//
//
//
//
//        return CustomViewHolder(cardViewForPendingOrderDetails)
//
//
//    }
//
//    override fun onBindViewHolder(holder: CustomViewHolder?, position: Int) {
//
//
//
//        var iterator = 0
//
//        var delivered = "Unknown"
//
//        var quantity = "Unknown"
//
//        var itemName = "Unknown"
//
//        var orderDetails = HashMap<String,Any>()
//
//
////        val orderDetailasHashMap = allOrderDetailsInMap.get(position)
//
//        for (key in allOrderDetailsInMap.keys) {
//
//           if(iterator == position)
//
//
///////////////////////////////
//
//
//
//                var receivedKey = key as String
//
//                if (receivedKey == "delivered") {
//
//
//                    delivered = allOrderDetailsInMap[key] as String
//
//
//                } else {
//
//                    orderDetails = allOrderDetailsInMap[key] as HashMap<String, Any>
//
//
//                }
//
//
//
///////////////////////////////
//
//            iterator = iterator + 1
//
//        }
//
//
//        quantity = orderDetails["quantity"] as String
//
//        itemName = orderDetails["itemName"] as String
//
//
//        holder?.view?.LinearLayout1?.LinearLayout2?.LinearLayout3?.txtItemName?.text = itemName
//
//        holder?.view?.LinearLayout1?.LinearLayout2?.LinearLayout3?.txtQuantity?.text = "Quantity : " + quantity
//
//
//    }
//
//}
//
