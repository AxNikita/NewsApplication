package com.axproject.newsapplication.ui.fragment.everything

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.axproject.newsapplication.R
import com.axproject.newsapplication.databinding.FragmentEverythingBinding
import com.axproject.newsapplication.databinding.FragmentTopHeadliensBinding
import com.axproject.newsapplication.ui.fragment.topheadlines.TopHeadlinesViewModel
import com.axproject.newsapplication.utils.adapter.NewsListAdapter

class EverythingFragment : Fragment() {

    private lateinit var binding: FragmentEverythingBinding
    lateinit var viewModel: EverythingViewModel
    private lateinit var listAdapter: NewsListAdapter
    private lateinit var listLayoutManager: RecyclerView.LayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_everything, container, false)
        viewModel = ViewModelProvider(this).get(EverythingViewModel::class.java)
        viewModel.setContext(context!!)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        initNewsList()
    }


    private fun initNewsList() {
        listLayoutManager = LinearLayoutManager(context)
        listAdapter = NewsListAdapter()
        viewModel.getEverythingArticle().observeForever{
            println("ADD NEW NEWS")
            listAdapter.addCallLogList(it)
        }
        binding.everythingNewsList.apply {
            setHasFixedSize(true)
            layoutManager = listLayoutManager
            adapter = listAdapter
        }
    }

    private fun initSwipeRefresh() {
        binding.everythingNewsSwipeLayout.setOnRefreshListener {
            val runnable = Runnable {
                viewModel.deleteTables()
                viewModel.getEverythingArticle()
                binding.everythingNewsSwipeLayout.isRefreshing = false
            }

            val handler = Handler()
            handler.postDelayed(
                runnable, 3000.toLong()
            )
        }
    }
}