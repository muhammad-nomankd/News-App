package com.example.newsapp

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NewsAdapter(private val context: Context, private val list: List<Article>) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titletext: TextView = itemView.findViewById(R.id.titletext)
        val descriptiontxt: TextView = itemView.findViewById(R.id.descriptiontext)
        val imageviewi: ImageView = itemView.findViewById(R.id.imageviewi)
        val readMore: TextView = itemView.findViewById(R.id.readMore)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)
        return NewsViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val article = list[position]
        holder.titletext.text = list[position].title
        holder.descriptiontxt.text = list[position].description

        Glide.with(context)
            .load(list[position].urlToImage)
            .into(holder.imageviewi)

        holder.descriptiontxt.setOnClickListener {
            toggleDescription(holder.descriptiontxt, holder.readMore)
        }
        holder.readMore.setOnClickListener {
            toggleDescription(holder.descriptiontxt, holder.readMore)
        }

        holder.itemView.setOnClickListener {
            val intent = Intent(context, ArticleActivity::class.java)
            intent.putExtra("title", list[position].title)
            intent.putExtra("content", list[position].content)
            intent.putExtra("image", list[position].urlToImage)
            context.startActivity(intent)
        }
    }

    private fun toggleDescription(descriptionTextView: TextView, readMoreTextView: TextView)
    {

        val isTruncated = descriptionTextView.maxLines == 2
        descriptionTextView.maxLines = if (isTruncated) Int.MAX_VALUE else 2
        if(isTruncated)
        {
           descriptionTextView.maxLines= Int.MAX_VALUE
        }
        else 2
        if (isTruncated)
        {
          readMoreTextView.visibility=View.GONE
        }
        else  readMoreTextView.visibility=View.VISIBLE
    }
}
