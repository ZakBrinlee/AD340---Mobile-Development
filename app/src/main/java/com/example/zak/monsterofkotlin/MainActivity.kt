package com.example.zak.monsterofkotlin

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import android.support.design.widget.FloatingActionButton
import android.view.View
import android.R.id.button1




class MainActivity : AppCompatActivity() {

    lateinit var aboutText: TextView;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        aboutText = findViewById(R.id.aboutText)
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener { onClick()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun onClick(){

        //this will check the visibility of the aboutText
        val aboutTextIsVisible = aboutText.getVisibility()

        //This will show or hide the aboutText depending on it's current visibility
        if(aboutTextIsVisible==View.INVISIBLE)
        {
            aboutText.setVisibility(View.VISIBLE)
        }
        else if(aboutTextIsVisible==View.VISIBLE)
        {
            aboutText.setVisibility(View.INVISIBLE);
        }

    }
}
