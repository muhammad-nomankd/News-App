package com.example.newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class ArticleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)
        supportActionBar?.hide()

        val title = intent.getStringExtra("title")
        val image = intent.getStringExtra("image")
        val content = intent.getStringExtra("content")
        val articleTitle: TextView = findViewById(R.id.articletitle)
        val imagearticle: ImageView = findViewById(R.id.article_image)
        val articlecontent: TextView = findViewById(R.id.articlecontent)

        articleTitle.text = title
        articlecontent.text = content // Use the correct variable name
        Glide.with(this)
            .load(image)
            .into(imagearticle)
    }
}
