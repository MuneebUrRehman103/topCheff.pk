package com.example.themuneeb.myfristapp

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.custom_view_recycler_standard_menu.view.*

/**
 * Created by TheMuneeb on 3/25/2018.
 */



class AdapterOfRecyclyerViewStandarMenu : RecyclerView.Adapter<CustomViewHolder>(){


    val values = listOf("Biryani","Haleem","Nihari","Kofte","Kabab","Tikka")

    val imagValues = listOf(R.drawable.abc_btn_radio_material,
                    R.drawable.abc_cab_background_internal_bg,
                    R.drawable.abc_ab_share_pack_mtrl_alpha,
                    R.drawable.abc_ic_ab_back_material,
                    R.drawable.abc_ic_star_black_16dp,
                    R.drawable.common_google_signin_btn_icon_light);

    override fun getItemCount(): Int {
        return values.size

    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CustomViewHolder {

        val layoutInflator = LayoutInflater.from(parent?.context)
//        val cellForRow = CardView(parent?.context)
//
//
//        val textView1 : TextView = TextView(parent?.context)
//
//        textView1.text = "Deal1"
//
//        val textView2 : TextView = TextView(parent?.context)
//
//        textView2.text = "Deal2"
//
//
//        cellForRow.addView(textView1)
//        cellForRow.addView(textView2)


        val cellForRow =  layoutInflator.inflate(R.layout.custom_view_recycler_standard_menu,parent,false)






        return CustomViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolder?, position: Int) {


        val value = values.get(position)
        val imagValue = imagValues.get(position)

        holder?.view?.txtMenuTitle?.text = value
        holder?.view?.imgMenu?.setImageResource(imagValue)


    }

}



class CustomViewHolder(val view: View) : RecyclerView.ViewHolder(view){}