package com.example.themuneeb.myfristapp.Database

/**
 * Created by TheMuneeb on 4/7/2018.
 */

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteQueryBuilder
import com.example.themuneeb.myfristapp.Model.Order
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper

private val DBName = "topCheffDB.db"
private val DBVersion = 1

class Database(context: Context?) : SQLiteAssetHelper(context, DBName, null, DBVersion) {


    // cart -> Database

    // task 1 : make getAddedMenuItemsFromCart  method and collect all the items in the array of type orders that will be
    // returned to who ever will call it

    fun getItemsAddedToCart () : List<Order> {

        var listOfOrdersThatAreAdded : MutableList<Order> = arrayListOf()

        val databaseInstance : SQLiteDatabase = readableDatabase

        val query = "SELECT * FROM OrderDetail ; "


        val cursorOfTheRowsReturnedFromDatabase = databaseInstance.rawQuery(query,null)





        if (cursorOfTheRowsReturnedFromDatabase.count != 0){

            cursorOfTheRowsReturnedFromDatabase.moveToFirst()
            do{




                var productId = cursorOfTheRowsReturnedFromDatabase.getString(cursorOfTheRowsReturnedFromDatabase.getColumnIndex("ProductId"))
                var productName = cursorOfTheRowsReturnedFromDatabase.getString(cursorOfTheRowsReturnedFromDatabase.getColumnIndex("ProductName"))
                var productQuantity = cursorOfTheRowsReturnedFromDatabase.getString(cursorOfTheRowsReturnedFromDatabase.getColumnIndex("Quantity"))
                var productDiscount = cursorOfTheRowsReturnedFromDatabase.getString(cursorOfTheRowsReturnedFromDatabase.getColumnIndex("Discount"))
                var productPrice = cursorOfTheRowsReturnedFromDatabase.getString(cursorOfTheRowsReturnedFromDatabase.getColumnIndex("Price"))


                var order = Order(productId,productName,productQuantity,productPrice,productDiscount)


                val name = productName

                println(" ordername :: $name + price :: ${order.price}  ")

                listOfOrdersThatAreAdded.add(order)

            }while(cursorOfTheRowsReturnedFromDatabase.moveToNext())

        }



        return listOfOrdersThatAreAdded

    }



    //Task 2 : addItemsToCart Method


    fun addItemsToCart(order: Order) {


        val databaseInstance = writableDatabase

        val queryForInsertingItems = "INSERT INTO OrderDetail(ProductId,ProductName,Quantity,Price,Discount) VALUES ('${order.productId}','${order.productName}','${order.quantity}','${order.price}', '${order.discount}' ); "

        databaseInstance.execSQL(queryForInsertingItems)


    }

    //Task 3 : cleanAllItemsFromCart Method to remove method when the order is placed


    fun removeAllItemsFromCart() {

        val databaseInstance = readableDatabase

        val queryForDeletingAllCartItems = "DELETE FROM OrderDetail ;"

        databaseInstance.execSQL(queryForDeletingAllCartItems)

    }



    //Task 4 : call the above methods from the cart button in menudetails activity



}