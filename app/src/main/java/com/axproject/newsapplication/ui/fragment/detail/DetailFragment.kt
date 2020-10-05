package com.axproject.newsapplication.ui.fragment.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.axproject.newsapplication.R
import com.axproject.newsapplication.data.database.NewsDatabase
import com.axproject.newsapplication.data.model.Article
import com.axproject.newsapplication.databinding.FragmentDetailBinding
import com.axproject.newsapplication.databinding.FragmentEverythingBinding
import com.axproject.newsapplication.ui.fragment.everything.EverythingViewModel
import com.axproject.newsapplication.utils.adapter.NewsListAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.news_item.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private lateinit var viewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        viewModel.setContext(context!!)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
            viewModel.getArticleByTitle(arguments!!.getString("title")).observeForever {
                binding.newsAuthor.text = it.author
                binding.newsPublishedAt.text = it.publishedAt
                binding.newsTitle.text = it.title
                Picasso.get().load(it.urlToImage).into(binding.newsImg)
                binding.newsDescription.text = it.description
            }
    }

}