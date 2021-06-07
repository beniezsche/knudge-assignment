package com.beniezsche.knudgeassignment.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.beniezsche.knudgeassignment.R
import com.beniezsche.knudgeassignment.model.Article
import com.beniezsche.knudgeassignment.ui.FullArticleActivity
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat
import kotlin.collections.ArrayList

class ArticleAdapter: RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {

    var articles: ArrayList<Article>? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ArticleAdapter.ArticleViewHolder {
        return ArticleViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_article, parent, false))
    }

    override fun onBindViewHolder(holder: ArticleAdapter.ArticleViewHolder, position: Int) {

        val article = articles?.get(position)

        holder.headline.text = article?.title
        holder.source.text = article?.source?.name

        val formatFrom = SimpleDateFormat("yyyy-MM-dd")
        val formatTo = SimpleDateFormat("dd/MM/yyyy")

        val time = article!!.publishedAt

        holder.time.text = formatTo.format(formatFrom.parse(time)!!)

        Glide.with(holder.itemView.context).load(article.urlToImage).into(holder.bgImage)

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, FullArticleActivity::class.java)
            intent.putExtra("url",article.url)
            intent.putExtra("headline", article.title)
            holder.itemView.context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return if (articles == null) 0 else articles!!.size
    }

    inner class ArticleViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val headline: TextView = itemView.findViewById(R.id.headline)
        val source: TextView = itemView.findViewById(R.id.source)
        val time: TextView = itemView.findViewById(R.id.time)
        val bgImage: ImageView = itemView.findViewById(R.id.bgImage)

    }
}