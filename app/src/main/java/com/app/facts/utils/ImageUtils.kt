package com.app.facts.utils

import android.app.Activity
import android.util.DisplayMetrics
import android.view.View
import android.widget.ImageView
import com.app.facts.R
import com.bumptech.glide.Glide


object ImageUtils {

    fun loadImage(imageUrl: String?, imageView: ImageView?) {
        if (imageUrl.isNullOrEmpty()) {
            imageView?.visibility = View.GONE
            return
        }

        imageView?.visibility = View.VISIBLE
        Glide.with(imageView?.context)
                .load(imageUrl)
                .centerCrop()
                .crossFade()
                .placeholder(R.drawable.img_placeholder)
                .error(R.drawable.img_placeholder_error)
                .into(imageView)
    }


    fun imageWith(context:Activity):Int{
        val displayMetrics = DisplayMetrics()
        context.windowManager.defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics.widthPixels
    }

    fun imageHeight(context:Activity):Int{
        return (imageWith(context)/16)*9
    }
}