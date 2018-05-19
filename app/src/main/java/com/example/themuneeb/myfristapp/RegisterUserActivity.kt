package com.example.themuneeb.myfristapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.themuneeb.myfristapp.Database.Database
import com.example.themuneeb.myfristapp.Model.Order
import com.example.themuneeb.myfristapp.Model.User
import kotlinx.android.synthetic.main.activity_register_user.*

class RegisterUserActivity : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_user)




        btnRegister.setOnClickListener{

            if ( txtUsername.text.toString() != "" && txtEmailAddress.text.toString() != "" && txtPassword.text.toString() != "" && txtAddress.text.toString() != "" && txtPhoneNo.text.toString() != "" ){

                val username = txtUsername.text.toString()
                val email = txtEmailAddress.text.toString()
                val password = txtPassword.text.toString()
                val phoneno = txtPhoneNo.text.toString()
                val address = txtAddress.text.toString()


                registerUserToLocalDataBase(username,email,password,phoneno,address)






            }
            else {

                Toast.makeText(
                        this,
                        "Please make sure all the required details are entered ! ",
                        Toast.LENGTH_SHORT
                ).show()


            }







        }


    }



    fun registerUserToLocalDataBase(username : String , email : String , password : String , phoneno : String , address : String) {




        Toast.makeText(
                this,
                "Welcome " + username + " to TopChef's World",
                Toast.LENGTH_SHORT
        ).show()


        val intent = Intent(this,MainMenuActivity::class.java)

        startActivity(intent)



    }




}
