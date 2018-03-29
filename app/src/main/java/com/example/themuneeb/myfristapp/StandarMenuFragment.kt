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
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [StandarMenuFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [StandarMenuFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StandarMenuFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {


        val view: View
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        view = inflater.inflate(R.layout.fragment_standar_menu,container,false)
        view.recViewStandardMenu.layoutManager = LinearLayoutManager(activity)

        view.recViewStandardMenu.adapter = AdapterOfRecyclyerViewStandarMenu()

        return view
    }



}




