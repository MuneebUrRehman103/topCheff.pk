package com.example.themuneeb.myfristapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.text.Editable
import android.util.Log
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.activity_phone_no_login.*
import java.util.concurrent.TimeUnit
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential




class PhoneNoLoginActivity : AppCompatActivity() {


    private var mAuth = FirebaseAuth.getInstance()



    override fun onCreate(savedInstanceState: Bundle?) {



        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phone_no_login)

      val  mCallbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            override fun onVerificationCompleted(credential: PhoneAuthCredential) {

                progressBar.visibility = View.INVISIBLE


                txtLoginTextBoxTitle.isCursorVisible = false
                txtLoginTextBoxTitle.isClickable = false
                txtLoginTextBoxTitle.isFocusable = false

                txtLoginTextBoxTitle.setText("Verification Code Received")

                val selectedMenuOptionName = "Catering"

                val intent = Intent(this@PhoneNoLoginActivity,MainMenuActivity::class.java)

                startActivity(intent)



            }

            override fun onVerificationFailed(e: FirebaseException) {
                // This callback is invoked in an invalid request for verification is made,
                // for instance if the the phone number format is not valid.


            }


        }

//        override fun onCodeSent(verificationId: String?,
//                                token: PhoneAuthProvider.ForceResendingToken?) {
//            // The SMS verification code has been sent to the provided phone number, we
//            // now need to ask the user to enter the code and then construct a credential
//            // by combining the code with a verification ID.
//            Log.d(FragmentActivity.TAG, "onCodeSent:" + verificationId!!)
//
//            // Save verification ID and resending token so we can use them later
//            mVerificationId = verificationId
//            mResendToken = token
//
//            // ...
//        }



////      private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
//        mAuth.signInWithCredential(credential)
//                .addOnCompleteListener(this) { task ->
//                    if (task.isSuccessful) {
//                        // Sign in success, update UI with the signed-in user's information
//                        showSnackbar("signInWithCredential:success")
//  } else {
//                        // Sign in failed, display a message and update the UI
//                        showSnackbar("signInWithCredential:failure")
//                        if (task.exception is FirebaseAuthInvalidCredentialsException) {
//                            // The verification code entered was invalid
//                            showSnackbar("Invalid code was entered")
//                        }
//                        // Sign in failed
//                    }
//                }
//    }






        btnLogin.setOnClickListener {

            val phoneNumber = txtLoginTextBoxTitle.text.toString()

            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                    phoneNumber,
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







    }


    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = mAuth.getCurrentUser()


        if  (currentUser!=null) {

            val intent = Intent(this, GeneralMenuForSelectedCategoryActivity::class.java)

            startActivity(intent)


        }

        else{



        }





    }







}
