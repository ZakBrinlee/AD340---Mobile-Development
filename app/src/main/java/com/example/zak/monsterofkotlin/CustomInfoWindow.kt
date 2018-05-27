package com.example.zak.monsterofkotlin

import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class CustomInfoWindow(var mContext: LocationActivity) : GoogleMap.InfoWindowAdapter {

    private val v: View;
    private var markerId = "";

    init {
        v = LayoutInflater.from(mContext).inflate(R.layout.camera_info, null)
    }


    override fun getInfoWindow(marker: Marker): View? {
        return null
    }

    override fun getInfoContents(marker: Marker): View? {
        val cameraName = v.findViewById(R.id.cameraName) as TextView
        cameraName.text = marker.title

        val cameraImg =  v.findViewById(R.id.cameraImage) as ImageView
        Picasso.get().load(marker.snippet).into(cameraImg, InfoWindowRefresher(marker))

        return v

    }

    inner class InfoWindowRefresher(private val markerToRefresh: Marker) : Callback {

        override fun onSuccess() {
            if (!markerToRefresh.id.equals(markerId)) {
                markerId = markerToRefresh.id
                markerToRefresh.showInfoWindow()
            }
        }

        override fun onError(e: Exception) {}
    }

}

