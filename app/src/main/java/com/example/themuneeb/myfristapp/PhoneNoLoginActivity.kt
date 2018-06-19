package com.example.themuneeb.myfristapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.text.Editable
import android.util.Log
import android.view.View
import com.example.themuneeb.myfristapp.Database.Database
import com.example.themuneeb.myfristapp.Model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.activity_phone_no_login.*
import java.util.concurrent.TimeUnit
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.database.*


class PhoneNoLoginActivity : AppCompatActivity() {


    private var mAuth = FirebaseAuth.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phone_no_login)


       var isUserLoggedIn = checkIfUserIsAlreadySignedIn()






    }


    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = mAuth.getCurrentUser()


        if (currentUser != null) {

            val intent = Intent(this, GeneralMenuForSelectedCategoryActivity::class.java)

            startActivity(intent)


        } else {


        }


    }


    fun checkIfUserIsAlreadySignedIn(): Boolean {

            val database = Database(this)

            val sessionIdOfUserFromDatabase = database.getUserSessionId()
            val userIdFromDatabase = database.getUserId()

            var sessionMatches = false

            if (sessionIdOfUserFromDatabase != null) {


                val firebaseInst = FirebaseDatabase.getInstance().getReference("Users")


                firebaseInst.child(userIdFromDatabase).child("unwantedKey").setValue("786")

                firebaseInst.addValueEventListener(object : ValueEventListener {
                    override fun onCancelled(p0: DatabaseError?) {
                    }

                    override fun onDataChange(dataSnapshot: DataSnapshot?) {


                        val keyValuePairForUserId = dataSnapshot?.value as HashMap<String, Any>

                        val hashMapValuesUnderUserId = keyValuePairForUserId[userIdFromDatabase] as HashMap<String, Any>

                        val sessionIdValueFromFirebase = hashMapValuesUnderUserId["sessionId"]


                        if (sessionIdOfUserFromDatabase == sessionIdValueFromFirebase.toString()) {


                            sessionMatches = true


                            val intent = Intent(this@PhoneNoLoginActivity, MainMenuActivity::class.java)

                            startActivity(intent)



                            firebaseInst.removeEventListener(this)


                        }else{

                            btnLogin.setOnClickListener {

                                val phoneNumber = txtLoginTextBoxTitle.text.toString()

                                verifyUsersPhoneNumber(phoneNumber)


                                enterUserInFirebaseViaUserIdIEPhoneno("03122685832")

                            }

                            firebaseInst.removeEventListener(this)
                        }


                    }


                })



                return sessionMatches

            } else {


                return sessionMatches


            }


    }


    fun verifyUsersPhoneNumber(phoneNo: String) {


        val mCallbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            override fun onVerificationCompleted(credential: PhoneAuthCredential) {

                progressBar.visibility = View.INVISIBLE


                txtLoginTextBoxTitle.isCursorVisible = false
                txtLoginTextBoxTitle.isClickable = false
                txtLoginTextBoxTitle.isFocusable = false

                txtLoginTextBoxTitle.setText("Verification Code Received")

                val selectedMenuOptionName = "Catering"


                enterUserInFirebaseViaUserIdIEPhoneno(phoneNo)


            }

            override fun onVerificationFailed(e: FirebaseException) {
                // This callback is invoked in an invalid request for verification is made,
                // for instance if the the phone number format is not valid.


            }


//            override fun onCodeSent(verificationId: String?, p1: PhoneAuthProvider.ForceResendingToken?) {
//                super.onCodeSent(verificationId, p1)
//
//
//                //   The SMS verification code has been sent to the provided phone number, we
//                // now need to ask the user to enter the code and then construct a credential
//                // by combining the code with a verification ID
//                print("onCodeSent:" + verificationId!!)
//
//
//            }

        }






        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNo,
                60,
                TimeUnit.SECONDS,
                this,
                mCallbacks)


        progressBar.visibility = View.VISIBLE



        txtLoginTextBoxTitle.isCursorVisible = false
        txtLoginTextBoxTitle.isClickable = false
        txtLoginTextBoxTitle.isFocusable = false
        txtLoginTextBoxTitle.setText("Please Wait Verifing")

    }


    fun enterUserInFirebaseViaUserIdIEPhoneno(phoneNo: String) {

        val firebaseDatabaseRef = FirebaseDatabase.getInstance().getReference("Users")
        val database = Database(this)

//        val msg : HashMap<String,String> = HashMap<String,String>()
//
//        msg.put("username",message)
//        msg.put("email",username)
//        msg.put("password",username)
//        msg.put("phoneno",username)
//        msg.put("address",username)
//
//        firebaseDatabaseRef.child(userId).child(sessionId).child("userData").setValue(msg)


        // userId = Phoneno

        firebaseDatabaseRef.child(phoneNo).child("unwantedKey").setValue("786")

        firebaseDatabaseRef.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError?) {

            }

            override fun onDataChange(dataSnapshot: DataSnapshot?) {

                val keyValuePairForUserId = dataSnapshot?.value as HashMap<String, Any>

                val hashMapValuesUnderUserId = keyValuePairForUserId[phoneNo] as HashMap<String, Any>

                val sessionIdValue = hashMapValuesUnderUserId["sessionId"]


                var userLogedInForFirstTime = false

                if (sessionIdValue == null) {

                    userLogedInForFirstTime = true

                    val seesionKey = "10000000000"
                    val sessionIdForUserLogedIn = seesionKey


                    var intent = Intent(this@PhoneNoLoginActivity, RegisterUserActivity::class.java)


                    intent.putExtra("phoneNo", phoneNo)
                    intent.putExtra("sessionId", sessionIdForUserLogedIn)


                    startActivity(intent)
                    firebaseDatabaseRef.child(phoneNo).child("sessionId").setValue(sessionIdForUserLogedIn)

                    firebaseDatabaseRef.removeEventListener(this)

                } else {

                    if (!userLogedInForFirstTime) {


                        val newSessionId = sessionIdValue.toString()
                        var newSessionId2 = newSessionId.toLong()

                        val newSessionId3 = ++newSessionId2

                        val intent = Intent(this@PhoneNoLoginActivity, MainMenuActivity::class.java)

                        startActivity(intent)
                        firebaseDatabaseRef.child(phoneNo).child("sessionId").setValue(newSessionId3)


                        database.updateUserSessionId(newSessionId3.toString())


                        var sessionId  = database.getUserId()

                        firebaseDatabaseRef.removeEventListener(this)

                    }
                }


            }


        })


    }


}
