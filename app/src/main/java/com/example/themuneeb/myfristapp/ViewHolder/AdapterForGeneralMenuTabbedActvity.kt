package com.example.themuneeb.myfristapp.ViewHolder

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * Created by TheMuneeb on 4/14/2018.
 */


class AdapterForGeneralMenuTabbedActvity(fragmentManager : FragmentManager ) : FragmentPagerAdapter(fragmentManager) {


    val listOfFragments  = mutableListOf<Fragment>()

    val listOfFragmentNames  = mutableListOf<String>()


    fun addFragment( fragment : Fragment , fragmentName : String){

        listOfFragments.add(fragment)
        listOfFragmentNames.add(fragmentName)


    }

    override fun getCount(): Int {

           return listOfFragments.size
    }

    override fun getItem(position: Int): Fragment {

            return listOfFragments.get(position)
        }


    override fun getPageTitle(position: Int): CharSequence {

        return listOfFragmentNames.get(position)

    }

}
