package com.example.zak.monsterofkotlin

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import android.support.design.widget.FloatingActionButton
import android.view.View
import android.R.id.button1
import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.support.v4.widget.DrawerLayout
import android.support.design.widget.NavigationView
import android.support.v4.content.ContextCompat.startActivity
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBar
import android.support.v7.app.ActionBarDrawerToggle
import android.widget.*
import com.example.zak.monsterofkotlin.R.id.*
import kotlinx.android.synthetic.main.app_bar_main.*


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener{

    lateinit var editText: EditText;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

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

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
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
            R.id.action_settings -> {
                Toast.makeText(this@MainActivity, "Settings Menu Option Selected", Toast.LENGTH_LONG).show()
                true
            }
            R.id.action_theme -> {
                Toast.makeText(this@MainActivity, "What theme do you want?", Toast.LENGTH_LONG).show()
                true
                true
            }
            R.id.action_about -> {
                val intent = Intent(this@MainActivity, AboutActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.action_search -> {
                true
            }
            else -> {super.onOptionsItemSelected(item)}
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_movies -> {
                val intent = Intent(this@MainActivity, MovieRecyclerActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.nav_about -> {
                val intent = Intent(this@MainActivity, AboutActivity::class.java)
                startActivity(intent)
                true
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

}
