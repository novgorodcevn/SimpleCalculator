package com.codeliner.simplecalc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide

class AboutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        val url = "https://img2.fonwall.ru/o/ot/nature-animals-cat-wxdf.jpg"
        val imageFoto = findViewById<ImageView>(R.id.photo)
        Glide.with(this)
            .load(url)
            .into(imageFoto)
    }
}