package com.mina_magid.benshty

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_test.*
import java.util.ArrayList

class TestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        saveButton.setOnClickListener {
            val firstName = inputFirstName.text.toString()
            val lastName = inputLastName.text.toString()

            saveFireStore(firstName, lastName)

        }
        readFireStoreData()
    }

    fun saveFireStore(firstname: String, lastname: String) {
        val db = FirebaseFirestore.getInstance()
        val user: MutableMap<String, Any> = HashMap()
        user["firstName"] = firstname
        user["lastName"] = lastname

        db.collection("users")
            .add(user)
            .addOnSuccessListener {
                Toast.makeText(this@TestActivity, "record added successfully ", Toast.LENGTH_SHORT ).show()
            }
            .addOnFailureListener{
                Toast.makeText(this@TestActivity, "record Failed to add ", Toast.LENGTH_SHORT ).show()
            }
        readFireStoreData()
    }

    fun readFireStoreData() {
        val news: ArrayList<News> = ArrayList<News>()

        val db = FirebaseFirestore.getInstance()
        db.collection("users")
            .get()
            .addOnCompleteListener {

                val result: StringBuffer = StringBuffer()

                if(it.isSuccessful) {
                    for(document in it.result!!) {
                        result.append(document.data.getValue("firstName")).append(" ")
                            .append(document.data.getValue("lastName")).append("\n\n")

//                        news.add(News(document.data.getValue("amount").toString(),
//                            document.data.getValue("name").toString() ,
//                            document.data.getValue("price").toString()))
                    }
                    var _adapter = NewsAdapter(news)
                    rv2.adapter = _adapter
                    _adapter.notifyDataSetChanged()
                    Log.d(TAG, "${news.size}")
                    }
                    textViewResult.setText(result)
                }
            }

}