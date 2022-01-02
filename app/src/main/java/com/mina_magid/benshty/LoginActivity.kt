package com.mina_magid.benshty


import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseException
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.activity_login.*
import java.util.concurrent.TimeUnit

class LoginActivity : AppCompatActivity() {
    companion object{
        var mAuth: FirebaseAuth? = FirebaseAuth.getInstance()
        var phoneNumber: String? = null
        var otpid: String? = null

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        phoneNumber = "+201100581958"

        initiateOtp()

//        val smsVerifyCatcher :SmsVerifyCatcher = SmsVerifyCatcher(this, object : OnSmsCatchListener<String?>() {
//            fun onSmsCatch(message: String?) {
//                val code: String = parseCode(message) //Parse verification code
//                etCode.setText(code) //set code in edit text
//                //then you can send verification code to server
//            }
//        })

        b2.setOnClickListener(View.OnClickListener {
            when {
                t2.text.toString().isEmpty() -> Toast.makeText(applicationContext,
                        "Blank Field can not be processed",
                        Toast.LENGTH_LONG).show()
                t2.text.toString().length != 6 -> Toast.makeText(
                        applicationContext, "invalid OTP", Toast.LENGTH_LONG).show()
                else -> {
                    val credential = PhoneAuthProvider.getCredential(otpid!!, t2.text.toString())
                    signInWithPhoneAuthCredential(credential)
                }
            }
        })
    }

    private fun initiateOtp() {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber.toString(),  // Phone number to verify
                60,  // Timeout duration
                TimeUnit.SECONDS,  // Unit of timeout
                this,  // Activity (for callback binding)
                object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    override fun onCodeSent(s: String, forceResendingToken: PhoneAuthProvider.ForceResendingToken) {
                        otpid = s
                        Log.d(ContentValues.TAG,"otpid: $s ")
                    }

                    override fun onVerificationCompleted(phoneAuthCredential: PhoneAuthCredential) {
                        val code = phoneAuthCredential.smsCode
                        Log.d(ContentValues.TAG,"code: $code || credential:$phoneAuthCredential ")
                        signInWithPhoneAuthCredential(phoneAuthCredential)
                    }

                    override fun onVerificationFailed(e: FirebaseException) {
                        Toast.makeText(applicationContext, e.message, Toast.LENGTH_LONG).show()
                    }
                }) // OnVerificationStateChangedCallbacks
    }


    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        mAuth!!.signInWithCredential(credential)
                .addOnCompleteListener(this
                ) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(applicationContext, "Signin success", Toast.LENGTH_LONG)

//                        startActivity(Intent(this@LoginActivity, DashboardActivity::class.java))
//                        finish()
                    } else {
                        Toast.makeText(applicationContext, "Signin Code Error", Toast.LENGTH_LONG)
                                .show()
                    }
                }
    }
}