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
import android.content.Intent
import android.widget.Button
import android.widget.EditText
import android.widget.Toast


class MainActivity : AppCompatActivity() {

    lateinit var aboutText: TextView;
    lateinit var editText: EditText;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        aboutText = findViewById(R.id.aboutText)
        editText = findViewById(R.id.editText)

        //set onclick and intent for HW2 button
        intentButton.setOnClickListener {
            val name = editText.text.toString()
            val intent = Intent(this@MainActivity, IntentActivity::class.java)
            intent.putExtra("Name", name)
            startActivity(intent)
        }

        //set onclick and toast for first Toast Button
        movieButton.setOnClickListener {
            val intent = Intent(this@MainActivity, MovieRecyclerActivity::class.java)
            startActivity(intent)
        }
        //set onclick and toast for first Toast Button
        toast1.setOnClickListener {
            Toast.makeText(this@MainActivity, "This is Toast Button 1", Toast.LENGTH_LONG).show()
        }
        //set onclick and toast for first Toast Button
        toast2.setOnClickListener {
            Toast.makeText(this@MainActivity, "This is Toast Button 2", Toast.LENGTH_LONG).show()
        }
        //set onclick and toast for first Toast Button
        toast3.setOnClickListener {
            Toast.makeText(this@MainActivity, "This is Toast Button 3", Toast.LENGTH_LONG).show()
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

}
