package com.example.booklibrary.feature

import android.annotation.SuppressLint
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.booklibrary.R

@SuppressLint("CheckResult")
@BindingAdapter("imageUrl")
fun ImageView.loadImage(url: String?) {
    val requestOptions = RequestOptions().apply {
        transforms(CenterCrop(), RoundedCorners(30))
        fallback(R.drawable.no_photo)
        placeholder(R.drawable.no_photo)
        error(R.drawable.no_photo)
    }
    Glide.with(context).load(url).apply(requestOptions).into(this)
}