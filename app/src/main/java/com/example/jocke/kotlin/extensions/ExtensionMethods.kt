package com.example.jocke.kotlin.extensions

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.squareup.picasso.Picasso

/**
 * Created by Jocke on 2017-07-09.
 */

fun View.visible(b: Boolean) {
    visibility = if (b) View.VISIBLE else View.GONE
}

fun toast(context: Context, string: String) {
    Toast.makeText(context, "{$string}", Toast.LENGTH_SHORT).show()
}

fun ImageView.setUrl(url: Int) {
    Picasso.with(this.context).load(url).fit().into(this)
}

fun ViewGroup.inflate(layoutRes: Int): View = LayoutInflater.from(context).inflate(layoutRes, this, false)
