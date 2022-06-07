package com.kotlindev.newsforyou.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kotlindev.newsforyou.R
import com.kotlindev.newsforyou.ui.models.Article
import kotlinx.android.synthetic.main.item_article_preview.view.*
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class NewsAdapter :RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>(){
    inner class  ArticleViewHolder (itemView: View):RecyclerView.ViewHolder(itemView)

    private val differCallback = object :DiffUtil.ItemCallback<Article>(){
        //diffUtil funtions:
        //It decides whether two objects are representing same items or not.
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }
        // It decides whether two items have same data or not. This method is called by DiffUtil only if areItemsTheSame() returns true.
        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem==newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_article_preview,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article =differ.currentList[position]
        val parser =  SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        val formatter = SimpleDateFormat("dd.MM.yyyy HH:mm")
        val formattedDate = formatter.format(parser.parse(article.publishedAt))
        holder.itemView.apply {
            Glide.with(this).load(article.urlToImage).into(ivArticleImage)
            tvSource.text=article.source?.name
            tvTitle.text =article.title
            //tvDescription.text=article.description
            tvPublishedAt.text=formattedDate.subSequence(0,10);
            setOnClickListener{
                onItemClickListener?.let{it(article)}
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private  var onItemClickListener:((Article)->Unit)? =null

    fun setOnClickListener(listener:(Article)->Unit){
        onItemClickListener =listener
    }
}