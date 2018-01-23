package com.oliva.antonio.dornatest.extensions

import android.webkit.URLUtil
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions.centerCropTransform
import com.bumptech.glide.request.RequestOptions.circleCropTransform

/**
 * Created by antonio
 */

fun ImageView.loadUrl(url: String?) {
    url?.let {
        if (URLUtil.isValidUrl(url)) {
            Glide.with(this.context)
                    .load(url)
                    .apply(centerCropTransform())
                    .into(this)
        }
    }
}

fun ImageView.loadUrlRounded(url: String?) {
    url?.let {
        if (URLUtil.isValidUrl(url)) {
            Glide.with(this.context)
                    .load(url)
                    .apply(centerCropTransform())
                    .apply(circleCropTransform())
                    .into(this)
        }
    }
}
