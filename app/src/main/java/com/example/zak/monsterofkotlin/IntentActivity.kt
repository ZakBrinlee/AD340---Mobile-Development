package com.example.zak.monsterofkotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_intent.*

class IntentActivity : AppCompatActivity() {

    private val TAG = "IntentActivity"

    lateinit var intentView: TextView;


    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate() Restoring previous state")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent)

        //get data from intent
        var intent = intent
        val name = intent.getStringExtra("Name")

        //starting debug logging using the logcat
        Log.d(TAG, "Took in name from intent: " + name)

        //intentView
        intentView = findViewById(R.id.intentView)
        //set text
        intentView.text = "Welcome "+name+""
        Log.d(TAG, "intentView loaded with intent name. End of onCreate()")
    }


}
