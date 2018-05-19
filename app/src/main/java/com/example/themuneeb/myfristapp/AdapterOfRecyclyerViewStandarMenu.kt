package com.example.themuneeb.myfristapp

import android.content.Intent
import android.graphics.Color
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.custom_view_recycler_standard_menu.view.*


/**
 * Created by TheMuneeb on 3/25/2018.
 */



class AdapterOfRecyclyerViewStandarMenu(val menuItems : com.example.themuneeb.myfristapp.Model.MenuItem) : RecyclerView.Adapter<CustomViewHolder>(){


    val values = listOf("Biryani","Haleem","Nihari","Kofte","Kabab","Tikka")

//    val imagValues = listOf(R.drawable.abc_btn_radio_material,
//                    R.drawable.abc_cab_background_internal_bg,
//                    R.drawable.abc_ab_share_pack_mtrl_alpha,
//                    R.drawable.abc_ic_ab_back_material,
//                    R.drawable.abc_ic_star_black_16dp,
//                    R.drawable.common_google_signin_btn_icon_light);

    override fun getItemCount(): Int {
        return  menuItems.menus.count()

    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CustomViewHolder {



        val cardMenuView =  LayoutInflater.from(parent?.context).inflate(R.layout.custom_view_recycler_standard_menu,parent,false)


        cardMenuView.setOnClickListener{

            val intent = Intent(parent?.context,MenuDetailsActivity(  menuItems.vendor_id.toString() )::class.java)

            startActivity(parent?.context,intent,null)

        }




        return CustomViewHolder(cardMenuView)


    }

    override fun onBindViewHolder(holder: CustomViewHolder?, position: Int) {

        // mueni_id :: title :: price :: range :: description :: category
        val menu = menuItems.menus.get(position)

        val menuItemName: String = menu.title
        val menuDetailForPerPersons: String = menu.range
        val menuDetailForCategory: String = menu.category
        val menuDetailForPerPrice: String = menu.price



        holder?.view?.cardForMenuItem?.linearLayoutForMenuDetail?.linearLayoutContainingMenuTitle?.txtItemName?.text = menuItemName
        holder?.view?.cardForMenuItem?.linearLayoutForMenuDetail?.linearLayoutContainingMenuTitle?.txtItemDescription?.text = menu.description

        holder?.view?.cardForMenuItem?.linearLayoutForMenuDetail?.linearLayoutContaingMenuInfo?.txtPerPerson?.text = menuDetailForPerPersons + " persons"
        holder?.view?.cardForMenuItem?.linearLayoutForMenuDetail?.linearLayoutContaingMenuInfo?.txtCategory?.text = "type : "+ menuDetailForCategory
        holder?.view?.cardForMenuItem?.linearLayoutForMenuDetail?.linearLayoutContaingMenuInfo?.txtPrice?.text = menuDetailForPerPrice + " RS"


        // holder?.view?.cardForMenuItem?.imgInCardRight = ""


        //styling

        if (position%2 == 0 ) {
            //green
            holder?.view?.cardForMenuItem?.setCardBackgroundColor(Color.rgb(34,153,36))
            //orange
            holder?.view?.cardForMenuItem?.linearLayoutForMenuDetail?.linearLayoutContaingMenuInfo?.txtPerPerson?.setTextColor(Color.rgb(250,158,20))
            holder?.view?.cardForMenuItem?.linearLayoutForMenuDetail?.linearLayoutContaingMenuInfo?.txtCategory?.setTextColor(Color.rgb(250,158,20))
            holder?.view?.cardForMenuItem?.linearLayoutForMenuDetail?.linearLayoutContainingMenuTitle?.txtItemName?.setTextColor(Color.rgb(250,158,20))

            holder?.view?.cardForMenuItem?.linearLayoutForMenuDetail?.linearLayoutContaingMenuInfo?.txtPrice?.setTextColor(Color.WHITE)

        }
        else{
            //orange
            holder?.view?.cardForMenuItem?.setCardBackgroundColor(Color.rgb(250,158,20))
            //green
            holder?.view?.cardForMenuItem?.linearLayoutForMenuDetail?.linearLayoutContaingMenuInfo?.txtPerPerson?.setTextColor(Color.rgb(34,153,36))
            holder?.view?.cardForMenuItem?.linearLayoutForMenuDetail?.linearLayoutContaingMenuInfo?.txtCategory?.setTextColor(Color.rgb(34,153,36))
            holder?.view?.cardForMenuItem?.linearLayoutForMenuDetail?.linearLayoutContainingMenuTitle?.txtItemName?.setTextColor(Color.rgb(34,153,36))

            holder?.view?.cardForMenuItem?.linearLayoutForMenuDetail?.linearLayoutContaingMenuInfo?.txtPrice?.setTextColor(Color.WHITE)



        }


    }

}



class CustomViewHolder(val view: View) : RecyclerView.ViewHolder(view){}