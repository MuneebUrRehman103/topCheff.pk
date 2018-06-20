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
import com.example.themuneeb.myfristapp.Model.Menu
import com.example.themuneeb.myfristapp.Model.MenuItem
import com.example.themuneeb.myfristapp.Model.VendorDetail
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_standard_menu.*
import kotlinx.android.synthetic.main.fragment_standar_menu.view.*
import okhttp3.*
import java.io.IOException

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [StandarMenuFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [StandarMenuFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StandarMenuFragment : Fragment() {


    //(standardMenuLink) getcaterersbytype :: type : catering daawat lunchbox single_pick beemar  all vendor id will be same ..
        // :: http://topchef.pk/api/router.php?method=getMenusByVendorAndType&type=catering&vendor=2

    // (vendorMenuLink) getCaterersByType :: type : catering daawat lunchbox single_pick beemar
        // :: http://topchef.pk/api/router.php?method=getCaterers&type=catering

    // (menusOfVendorSelected) getmenusforspecificcaterers :: type : catering daawat lunchbox single_pick beemar
        // :: http://topchef.pk/api/router.php?method=getMenusByVendor&vendor=2




    var typeOfMenuToBeDisplayed = "MenuItems"  // typesOfMenus :: standardMenu : MenuItems , VendorMenu : VendorItems , VendorItemsSelectedMenu : vendorIdSelected
    var categoryOfMenuToBeDisplayed = "catering"
    var vendorIdBySelectingVendor = "2"

    //typeOfMenuToBeDisplayed ::  VendorItems || MenuItems


    val vendorIdToGetItsMenu = "2"

     //  val urlToGetMenuItems = "http://topchef.pk/api/router.php?method=getMenusByVendor&vendor="+vendorIdToGetItsMenu


    var vendorIdForStandardMenu = "2"
    var categoryOfStandardMenu = "catering"



    var urlToGetMenuItems = "http://topchef.pk/api/router.php?method=getMenusByVendorAndType&type="+categoryOfStandardMenu+"&vendor="+vendorIdForStandardMenu
    var urlToGetVendorItems = "http://topchef.pk/api/router.php?method=getCaterers&type="+categoryOfStandardMenu
    var urlToGetMenuForVendorSelected = "http://topchef.pk/api/router.php?method=getMenusByVendor&vendor="+vendorIdBySelectingVendor


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {


        val args = arguments


        val view: View
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        view = inflater.inflate(R.layout.fragment_standar_menu,container,false)
        view.recViewStandardMenu.layoutManager = LinearLayoutManager(activity)

        if (args != null){
            typeOfMenuToBeDisplayed = args.getString("typeOfMenu")

            if ( args.getString("categoryOfMenuSelected") != null){
                categoryOfMenuToBeDisplayed = args.getString("categoryOfMenuSelected")
            }



            if (args.getString("vendorIdBySelectingVendor") != null){


                vendorIdBySelectingVendor = args.getString("vendorIdBySelectingVendor")

            }




        }
        else{

            typeOfMenuToBeDisplayed = "MenuItems"
            categoryOfMenuToBeDisplayed = "catering"
        }


        // get api data for standar menu details from :: http://topchef.pk/api/router.php?method=getMenusByVendor&vendor=2


        // standardMenu : MenuItems

        if ( typeOfMenuToBeDisplayed=="MenuItems" ){

            categoryOfStandardMenu = categoryOfMenuToBeDisplayed


            if(categoryOfStandardMenu!=null){

                urlToGetMenuItems = "http://topchef.pk/api/router.php?method=getMenusByVendorAndType&type="+categoryOfStandardMenu+"&vendor="+vendorIdForStandardMenu
            }

            createMenuItemsFragment()
        }


        // vendorMenu : VendorItems

        if ( typeOfMenuToBeDisplayed=="VendorItems" ){

            categoryOfStandardMenu = categoryOfMenuToBeDisplayed

            if(categoryOfStandardMenu != null){

                urlToGetVendorItems = "http://topchef.pk/api/router.php?method=getCaterers&type="+categoryOfStandardMenu

            }

            createVendorItemsFragment()
        }


        // VendorItemsSelectedMenu || selectedVendorIdWillBePassed

        if ( typeOfMenuToBeDisplayed=="VendorItemsSelectedMenu" ){

            val vendorId = vendorIdBySelectingVendor

            if(vendorId != null) {
                urlToGetMenuForVendorSelected = "http://topchef.pk/api/router.php?method=getMenusByVendor&vendor=" + vendorId
            }
            createMenuItemsFragmentOfSelectedVendor()
        }






        return view
    }




    fun createMenuItemsFragment(){



        ///////////////////////////////////////


        var menuItems : MenuItem? = null

        val request = Request.Builder()
                .url(urlToGetMenuItems)
                .build()

        var client = OkHttpClient()



        client.newCall(request).enqueue(object : Callback {

            override fun onResponse(call: Call?, response: Response?) {

                val body = response?.body()?.string()
                println(body)

                val gson = GsonBuilder().create()

                var  menuItems = gson.fromJson(body,MenuItem::class.java)

                this.run {

                    activity.runOnUiThread {  recViewStandardMenu.adapter = AdapterOfRecyclyerViewStandarMenu(menuItems)  }


                }


                println(menuItems)


            }

            override fun onFailure(call: Call?, e: IOException?) {
            }


        })


    }


    fun createVendorItemsFragment(){



        ///////////////////////////////////////


        var menuItems : MenuItem? = null


        val request = Request.Builder()
                .url(urlToGetVendorItems)
                .build()

        var client = OkHttpClient()



        client.newCall(request).enqueue(object : Callback {

            override fun onResponse(call: Call?, response: Response?) {

                val body = response?.body()?.string()
                println(body)

                val gson = GsonBuilder().create()

                var  vendorDetails = gson.fromJson(body,VendorDetail::class.java)

                this.run {

                    activity.runOnUiThread {
                        recViewStandardMenu.adapter = com.example.themuneeb.myfristapp.ViewHolder.AdapterForRecyclerOfVendorMenu(vendorDetails)
                    }


                }


                println(menuItems)


            }

            override fun onFailure(call: Call?, e: IOException?) {
            }


        })

    }





    fun createMenuItemsFragmentOfSelectedVendor(){



        ///////////////////////////////////////


        var menuItems : MenuItem? = null

        val request = Request.Builder()
                .url(urlToGetMenuForVendorSelected)
                .build()

        var client = OkHttpClient()



        client.newCall(request).enqueue(object : Callback {

            override fun onResponse(call: Call?, response: Response?) {

                val body = response?.body()?.string()
                println(body)

                val gson = GsonBuilder().create()

                var  menuItems = gson.fromJson(body,MenuItem::class.java)

                this.run {

                    activity.runOnUiThread {  recViewStandardMenu.adapter = AdapterOfRecyclyerViewStandarMenu(menuItems)  }


                }


                println(menuItems)


            }

            override fun onFailure(call: Call?, e: IOException?) {
            }


        })


    }




}




