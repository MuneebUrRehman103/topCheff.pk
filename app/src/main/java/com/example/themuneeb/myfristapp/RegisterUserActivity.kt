package com.example.themuneeb.myfristapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.themuneeb.myfristapp.Database.Database
import com.example.themuneeb.myfristapp.Model.Order
import com.example.themuneeb.myfristapp.Model.User
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_phone_no_login.*
import kotlinx.android.synthetic.main.activity_register_user.*

class RegisterUserActivity : AppCompatActivity() {

    var phoneNo = ""
    var sessionId = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_user)

        val isClickedFromSideBar = intent.getStringExtra("isClickedFromSideBar")


        if (isClickedFromSideBar!=null && isClickedFromSideBar=="true"){

            setupProfileViewFromRegisterView()

        }



        if (isClickedFromSideBar!=null && isClickedFromSideBar=="true"){

            val database = Database(this)

            val userdetails = database.getUserRegisterDetail()

            val userDetail = userdetails[0]

            phoneNo = userDetail.phoneno
            sessionId = database.getUserSessionId()


        }else{

            var phoneNo1 = intent.getStringExtra("phoneNo")
            var sessionId1 = intent.getStringExtra("sessionId")

            phoneNo = phoneNo1.toString()
            sessionId = sessionId1.toString()

        }




        txtPhoneNo.setText(phoneNo)
        txtPhoneNo.isCursorVisible = false
        txtPhoneNo.isClickable = false
        txtPhoneNo.isFocusable = false

        btnRegister.setOnClickListener{






            if ( txtUsername.text.toString() != "" && txtEmailAddress.text.toString() != "" && txtPassword.text.toString() != "" && txtAddress.text.toString() != "" && txtPhoneNo.text.toString() != "" ) {



                        val username = txtUsername.text.toString()
                        val email = txtEmailAddress.text.toString()
                        val password = txtPassword.text.toString()
                        val phoneno = txtPhoneNo.text.toString()
                        val address = txtAddress.text.toString()


                        registerUserToLocalDataBase(sessionId, username, email, password, phoneno, address)
                        registerUserToFirebase(username, email, password, phoneno, address)



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

    fun setupProfileViewFromRegisterView() {


        btnRegister.text = "Save Changes"

        val database = Database(this)

        val userdetails = database.getUserRegisterDetail()

        val userDetail = userdetails[0]

        txtUsername.setText(userDetail.username)
        txtEmailAddress.setText(userDetail.email)
        txtPassword.setText(userDetail.password)
        txtPhoneNo.setText(userDetail.phoneno)
        txtAddress.setText(userDetail.address)


    }

    fun registerUserToFirebase(username : String , email : String , password : String , phoneno : String , address : String) {

        val firebaseDatabaseRefForUserData = FirebaseDatabase.getInstance().getReference("Users")


        val msg : HashMap<String,String> = HashMap<String,String>()

        msg.put("username",username)
        msg.put("email",email)
        msg.put("password",password)
        msg.put("phoneno",phoneno)
        msg.put("address",address)

        firebaseDatabaseRefForUserData.child(phoneno).child("userData").setValue(msg)




    }


        fun registerUserToLocalDataBase(userId : String ,username : String , email : String , password : String , phoneno : String , address : String) {


        var databaseInstance = Database(this)

        databaseInstance.deleteUserRegisterDetail()

        databaseInstance.addUserRegisterDetail(userId,username,email,password,phoneno,address)

        Toast.makeText(
                this,
                "Welcome " + username + " to TopChef's World",
                Toast.LENGTH_SHORT
        ).show()


        val intent = Intent(this,MainMenuActivity::class.java)

        startActivity(intent)



    }




}
