package com.example.zak.monsterofkotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie_view.*

class MovieViewActivity : AppCompatActivity() {

    lateinit var titleView2: TextView
    lateinit var yearView2: TextView
    lateinit var directorView: TextView
    lateinit var descriptionView: TextView
    lateinit var imageView: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_view)

        //get data from intent
        val intent = intent
        val title = intent.getStringExtra("Title")
        val year = intent.getStringExtra("Year")
        val director = intent.getStringExtra("Director")
        val image = intent.getStringExtra("Image")
        val description = intent.getStringExtra("Description")

        //get views
        titleView2 = findViewById(R.id.titleView2)
        yearView2 = findViewById(R.id.yearView2)
        directorView = findViewById(R.id.directorView)
        descriptionView = findViewById(R.id.descriptionView)
        imageView = findViewById(R.id.imageView)

        //set view content
        titleView2.setText("Title: " + title)
        yearView2.setText("Year: " + year)
        directorView.setText("Director: " + director)
        descriptionView.setText("Description: " + description)


        Picasso.get()
                .load(image)
                .resize(850, 850)
                .centerCrop()
                .error(R.mipmap.ic_launcher_round)
                .into(imageView)
    }
}
