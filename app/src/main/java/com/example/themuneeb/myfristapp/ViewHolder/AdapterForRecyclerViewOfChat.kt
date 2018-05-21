package com.example.themuneeb.myfristapp.ViewHolder

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import com.example.themuneeb.myfristapp.CustomViewHolder
import com.example.themuneeb.myfristapp.Model.Message
import com.example.themuneeb.myfristapp.R
import kotlinx.android.synthetic.main.custom_view_for_list_view_for_chat.view.*

/**
 * Created by TheMuneeb on 5/19/2018.
 */


class AdapterForRecyclerViewOfChat(var messages : MutableList<Message>) : RecyclerView.Adapter<CustomViewHolder>(){



    override fun getItemCount(): Int {

            return messages.count()





    }





    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CustomViewHolder {



        val chatMessageView =  LayoutInflater.from(parent?.context).inflate(R.layout.custom_view_for_list_view_for_chat,parent,false)





        return CustomViewHolder(chatMessageView)


    }

    override fun onBindViewHolder(holder: CustomViewHolder?, position: Int) {

        // messages

        var message = messages[position]

        var sender = message.sender
        var text = message.text
        var time = message.time




                holder?.view?.messageView?.messageView2?.cardView1?.linearView2?.txtSender?.text = sender
                holder?.view?.messageView?.messageView2?.cardView1?.linearView2?.txtText?.text = text
                holder?.view?.messageView?.messageView2?.cardView1?.linearView2?.txtTime?.text = time

        if (sender == "Admin") {

            val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT)
            params.gravity = Gravity.START

                    holder?.view?.messageView?.messageView2?.layoutParams = params




            holder?.view?.messageView?.messageView2?.cardView1?.linearView2?.txtSender?.setTextColor(Color.rgb(34,153,36))
          //  holder?.view?.messageView?.cardView1?.linearView2?.txtText?.setTextColor()
            holder?.view?.messageView?.messageView2?.cardView1?.linearView2?.txtTime?.setTextColor(Color.rgb(34,153,36))


            holder?.view?.messageView?.messageView2?.cardView1?.setCardBackgroundColor(Color.rgb(250,158,20))
        }else{


            val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT)
            params.gravity = Gravity.END

            holder?.view?.messageView?.messageView2?.layoutParams = params
            holder?.view?.messageView?.messageView2?.cardView1?.setCardBackgroundColor(Color.rgb(34,153,36))


            holder?.view?.messageView?.messageView2?.cardView1?.linearView2?.txtSender?.setTextColor(Color.rgb(250,158,20))
            holder?.view?.messageView?.messageView2?.cardView1?.linearView2?.txtTime?.setTextColor(Color.rgb(250,158,20))
        }

    }

}
