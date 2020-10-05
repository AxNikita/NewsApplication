package com.axproject.newsapplication.utils.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.axproject.newsapplication.R
import com.axproject.newsapplication.data.database.NewsDatabase
import com.axproject.newsapplication.data.model.Article
import com.axproject.newsapplication.data.model.Favorite
import com.axproject.newsapplication.ui.fragment.detail.DetailFragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.news_item.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class NewsListAdapter : RecyclerView.Adapter<NewsListAdapter.NewsViewHolder>(){

    private var newsList: MutableList<Article> = mutableListOf()
    private lateinit var fragment: Fragment

    class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {

        holder.itemView.news_author.text = newsList[position].author
        holder.itemView.news_published_at.text = newsList[position].publishedAt
        holder.itemView.news_title.text = newsList[position].title
        Picasso.get().load(newsList[position].urlToImage).into(holder.itemView.news_img)
        holder.itemView.news_description.text = newsList[position].description

        holder.itemView.news_save_img.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                NewsDatabase.getDatabase(holder.itemView.context).newsDao().insertFavorite(
                    Favorite(
                        newsList[position].title!!
                    )
                )
            }
            holder.itemView.news_save_img.background = ContextCompat.getDrawable(
                holder.itemView.context,
                R.drawable.ic_star_24
            )
        }

        holder.itemView.card_root.setOnClickListener {

            val nextFrag = DetailFragment()
            val bundle = Bundle()
            bundle.putString("title", newsList[position].title)
            nextFrag.arguments = bundle

            fragment.activity!!.supportFragmentManager.beginTransaction()
                .replace(R.id.viewPager, nextFrag, "findThisFragment")
                .addToBackStack(null)
                .commit()
        }
    }

    override fun getItemCount() = newsList.size

    fun addCallLogList(callLogList: List<Article>?, fragment: Fragment) {
        this.fragment = fragment
        if (callLogList != null){
            this.newsList.clear()
            this.newsList = callLogList.toMutableList()
            notifyDataSetChanged()
        }
    }

}