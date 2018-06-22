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
//import kotlinx.android.synthetic.main.custom_view_of_rec_view_for_menu_detail.view.*
//
///**
// * Created by imac on 22/06/2018.
// */
//
//
//class AdapterForRecyclerViewOfPendingOrdeDetails()  : RecyclerView.Adapter<CustomViewHolder>(){
//
//
//    val values = listOf("Biryani","Haleem","Nihari","Kofte","Kabab","Tikka")
//
////    val imagValues = listOf(R.drawable.abc_btn_radio_material,
////                    R.drawable.abc_cab_background_internal_bg,
////                    R.drawable.abc_ab_share_pack_mtrl_alpha,
////                    R.drawable.abc_ic_ab_back_material,
////                    R.drawable.abc_ic_star_black_16dp,
////                    R.drawable.common_google_signin_btn_icon_light);
//
//    override fun getItemCount(): Int {
//
//
//
//
//
//
//        return  menuItemDetail.items.count()
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CustomViewHolder {
//
//
//
//        val cardMenuView =  LayoutInflater.from(parent?.context).inflate(R.layout.custom_view_of_rec_view_for_menu_detail,parent,false)
//
//
//        cardMenuView.setOnClickListener{
//
//            val intent = Intent(parent?.context, MenuDetailsActivity::class.java)
//
//            ContextCompat.startActivity(parent?.context, intent, null)
//
//        }
//
//
//
//
//        return CustomViewHolder(cardMenuView)
//
//
//    }
//
//    override fun onBindViewHolder(holder: CustomViewHolder?, position: Int) {
//
//
//        val item = menuItemDetail.items.get(position)
//
//        val menuItemName: String = item.item_name
//        val menuDescription: String = item.description
//
//        holder?.view?.cardForMenuItem?.linearLayoutForMenuDetail?.linearLayoutContainingMenuDetailsTitleAndDescription?.txtItemName?.text = menuItemName
//        holder?.view?.cardForMenuItem?.linearLayoutForMenuDetail?.linearLayoutContainingMenuDetailsTitleAndDescription?.txtItemDescription?.text = menuDescription
//        // holder?.view?.cardForMenuItem?.imgInCardRight = ""
//
//
//        //styling
//
//        //orange
//        holder?.view?.cardForMenuItem?.setCardBackgroundColor(Color.rgb(250,158,20))
//
//        //green
//        holder?.view?.cardForMenuItem?.linearLayoutForMenuDetail?.linearLayoutContainingMenuDetailsTitleAndDescription?.txtItemName?.setTextColor(Color.rgb(34,153,36))
//
//
//    }
//
//}
//
