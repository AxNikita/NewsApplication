package com.axproject.newsapplication.utils.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.axproject.newsapplication.R
import com.axproject.newsapplication.data.model.Article
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.news_item.view.*

class NewsListPagedAdapter(diffUtilCallback : DiffUtil.ItemCallback<Article>) :
    PagedListAdapter<Article, NewsListPagedAdapter.NewsViewHolder>(diffUtilCallback) {

    private var newsList: MutableList<Article> = mutableListOf()

    class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)
        return NewsViewHolder(view)
    }

    override fun getItemCount() = newsList.size

    fun addCallLogList(callLogList : List<Article>?) {
        if (callLogList != null){
            this.newsList.clear()
            this.newsList = callLogList.toMutableList()
            notifyDataSetChanged()
        }
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.itemView.news_author.text = newsList[position].author
        holder.itemView.news_published_at.text = newsList[position].publishedAt
        holder.itemView.news_title.text = newsList[position].title
        Picasso.get().load(newsList[position].urlToImage).into(holder.itemView.news_img)
        holder.itemView.news_description.text = newsList[position].description
    }

}