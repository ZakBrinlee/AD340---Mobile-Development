package com.example.zak.monsterofkotlin

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_intent.*

class IntentActivity : AppCompatActivity() {

    private val TAG = "IntentActivity"

    lateinit var intentView: TextView
    lateinit var sharedView: TextView
    lateinit var sharedPrefsHelper: SharedPrefsHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate() Restoring previous state")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent)
        setSupportActionBar(findViewById(R.id.my_toolbar))

        // Get a support ActionBar corresponding to this toolbar and enable the Up button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //get data from intent
        val intent = intent
        val name = intent.getStringExtra("Name")

        //starting debug logging using the logcat
        Log.d(TAG, "Took in name from intent: " + name)

        //intentView
        intentView = findViewById(R.id.intentView)
        //set text
        val message = "Welcome "+name+""
        intentView.setText(message)
        Log.d(TAG, "intentView loaded with intent name. End of onCreate()")

        //sharedView
        sharedView = findViewById(R.id.sharedView)
        //set text from sharedPref
        sharedPrefsHelper = SharedPrefsHelper(getApplicationContext());
        var savedName = sharedPrefsHelper.getSharedPrefs();
        sharedView.text = "This is the content from SharedPreferences from first activity: $savedName"
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_intent, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> {
                Toast.makeText(this@IntentActivity, "Settings Menu Option Selected", Toast.LENGTH_LONG).show()
                true
            }
            R.id.action_signin -> {
                Toast.makeText(this@IntentActivity, "You will be taking to Sign In page", Toast.LENGTH_LONG).show()
                true
            }
            R.id.action_about -> {
                val intent = Intent(this@IntentActivity, AboutActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.action_search -> {
                true
            }
            else -> {super.onOptionsItemSelected(item)}
        }
    }

}