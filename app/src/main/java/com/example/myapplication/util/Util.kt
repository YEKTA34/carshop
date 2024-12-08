package com.example.myapplication.util

import android.content.Context
import android.widget.ImageView
import androidx.constraintlayout.widget.Placeholder
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

fun ImageView.gorselIndir(url:String?) {
val options=RequestOptions()
    Glide.with(context).setDefaultRequestOptions(options).load(url).into(this)
}
