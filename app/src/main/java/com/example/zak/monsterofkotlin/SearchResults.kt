package com.example.zak.monsterofkotlin

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.media.session.MediaButtonReceiver.handleIntent
import android.support.v7.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_search_results.*
import android.app.SearchManager



class SearchResults : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_results)
        setSupportActionBar(toolbar)

        val intent = intent
    }

    override fun onNewIntent(intent: Intent) {
        handleIntent(intent);
    }
    private fun handleIntent(intent: Intent) {

        if (Intent.ACTION_SEARCH == intent.action) {
            val query = intent.getStringExtra(SearchManager.QUERY)
            //use the query to search your data somehow
        }
    }
}
