package com.example.themuneeb.myfristapp.Database

/**
 * Created by TheMuneeb on 4/7/2018.
 */


import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper

private val DBName = "TopCheffDB.db"
private val DBVersion = 1

class Database(context: Context?) : SQLiteAssetHelper(context, DBName, null, DBVersion) {


    // cart -> Database

    // task 1 : make getAddedMenuItemsFromCart  method and collect all the items in the array of type orders that will be
    // returned to who ever will call it

    //Task 2 : addItemsToCart Method

    //Task 3 : cleanAllItemsFromCart Method to remove method when the order is placed



    //Task 4 : call the above methods from the cart button in menudetails activity



}