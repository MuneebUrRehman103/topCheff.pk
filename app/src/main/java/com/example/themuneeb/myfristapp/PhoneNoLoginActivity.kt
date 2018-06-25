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

import com.google.firebase.auth.FirebaseUser

import com.google.firebase.auth.AuthResult
import android.support.annotation.NonNull
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task


class PhoneNoLoginActivity : AppCompatActivity() {


    private var mAuth = FirebaseAuth.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phone_no_login)



        val database = Database(this)
        database.deleteUserRegisterDetail()
        database.addUserRegisterDetail("10000000009","Muneeb","muneeburrehman103@gmail.com","123#@!","03122685832","defence phase VII khayaban e khizri")



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


                        if (sessionIdOfUserFromDatabase == sessionIdValueFromFirebase.toString() && sessionIdOfUserFromDatabase != null) {


                            sessionMatches = true


                            val intent = Intent(this@PhoneNoLoginActivity, MainMenuActivity::class.java)

                            startActivity(intent)



                            firebaseInst.removeEventListener(this)


                        }else{

                            btnLogin.setOnClickListener {

                                val phoneNumber = txtLoginTextBoxTitle.text.toString()

                                verifyUsersPhoneNumber(phoneNumber)


                               // enterUserInFirebaseViaUserIdIEPhoneno("03122685832")

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
                // This callback will be invoked in two situations:
                // 1 - Instant verification. In some cases the phone number can be instantly
                //     verified without needing to send or enter a verification code.
                // 2 - Auto-retrieval. On some devices Google Play services can automatically
                //     detect the incoming verification SMS and perform verification without
                //     user action.
                // Log.d(FragmentActivity.TAG, "onVerificationCompleted:" + credential)

                txtLoginTextBoxTitle.setText("Verification Code Received")

                enterUserInFirebaseViaUserIdIEPhoneno(phoneNo)



                signInWithPhoneAuthCredential(credential)
            }

            override fun onVerificationFailed(e: FirebaseException) {
                // This callback is invoked in an invalid request for verification is made,
                // for instance if the the phone number format is not valid.

                txtLoginTextBoxTitle.isCursorVisible = true
                txtLoginTextBoxTitle.isClickable = true
                txtLoginTextBoxTitle.isFocusable = true
                txtLoginTextBoxTitle.hint = "Re-Enter Your Mobile Number"
                btnLogin.isClickable = true
                progressBar.visibility = View.INVISIBLE

                if (e is FirebaseAuthInvalidCredentialsException) {
                    // Invalid request
                    // ...
                } else if (e is FirebaseTooManyRequestsException) {
                    // The SMS quota for the project has been exceeded
                    // ...
                }

                // Show a message and update the UI
                // ...
            }

            override fun onCodeSent(verificationId: String?,
                                    token: PhoneAuthProvider.ForceResendingToken?) {
                // The SMS verification code has been sent to the provided phone number, we
                // now need to ask the user to enter the code and then construct a credential
                // by combining the code with a verification ID.
                //  Log.d(FragmentActivity.TAG, "onCodeSent:" + verificationId!!)

                // Save verification ID and resending token so we can use them later
                val mVerificationId = verificationId
                val mResendToken = token

                val verificationId = mVerificationId!! as String

                txtLoginTextBoxTitle.isCursorVisible = true
                txtLoginTextBoxTitle.isClickable = true
                txtLoginTextBoxTitle.isFocusable = true
                txtLoginTextBoxTitle.hint = "Enter The Code You Have Received"
                btnLogin.isClickable = true
                progressBar.visibility = View.INVISIBLE

                btnLogin.setOnClickListener {

                    txtLoginTextBoxTitle.isCursorVisible = false
                    txtLoginTextBoxTitle.isClickable = false
                    txtLoginTextBoxTitle.isFocusable = false
                    txtLoginTextBoxTitle.hint = "Please Wait"
                    btnLogin.isClickable = false

                    progressBar.visibility = View.VISIBLE

                    val code = txtLoginTextBoxTitle.text.toString()

                    if (code!=null){

                        val credential = PhoneAuthProvider.getCredential(verificationId, code)


                        signInWithPhoneAuthCredential(credential)

                        signInWithPhoneAuthCredential(credential)

                    }


                }




                // ...
            }
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

        btnLogin.isClickable = false










    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, object : OnCompleteListener<AuthResult> {
                    override fun onComplete(task: Task<AuthResult>) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                        //    Log.d(FragmentActivity.TAG, "signInWithCredential:success")

                            val user = task.getResult().getUser()
                            // ...
                        } else {
                            // Sign in failed, display a message and update the UI
                          //  Log.w(FragmentActivity.TAG, "signInWithCredential:failure", task.getException())
                            if (task.getException() is FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                            }
                        }
                    }
                })
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
