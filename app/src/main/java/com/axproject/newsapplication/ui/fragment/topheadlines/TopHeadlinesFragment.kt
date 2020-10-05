package com.axproject.newsapplication.ui.fragment.topheadlines

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.axproject.newsapplication.R
import com.axproject.newsapplication.data.model.Article
import com.axproject.newsapplication.databinding.FragmentTopHeadliensBinding
import com.axproject.newsapplication.utils.adapter.NewsListAdapter


class TopHeadlinesFragment : Fragment() {

    private lateinit var binding: FragmentTopHeadliensBinding
    lateinit var viewModel: TopHeadlinesViewModel
    private lateinit var listAdapter: NewsListAdapter
    private lateinit var listLayoutManager: RecyclerView.LayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_top_headliens, container, false)
        viewModel = ViewModelProvider(this).get(TopHeadlinesViewModel::class.java)
        viewModel.setContext(context!!)
        viewModel.initTimer()
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        initNewsList()
    }


    private fun initNewsList() {
        listLayoutManager = LinearLayoutManager(context)
        listAdapter = NewsListAdapter()
        viewModel.getTopArticle().observeForever{
            println("ADD NEW NEWS")
            listAdapter.addCallLogList(it, this)
        }
        binding.topNewsList.apply {
            setHasFixedSize(true)
            layoutManager = listLayoutManager
            adapter = listAdapter
        }
    }

}