package com.axproject.newsapplication.ui.acttivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.ViewPager
import com.axproject.newsapplication.R
import com.axproject.newsapplication.databinding.ActivityMainBinding
import com.axproject.newsapplication.ui.fragment.everything.EverythingFragment
import com.axproject.newsapplication.ui.fragment.topheadlines.TopHeadlinesFragment
import com.axproject.newsapplication.utils.adapter.NewsFragmentPagerAdapter
import com.axproject.newsapplication.utils.animation.ZoomOutPageTransformer

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.viewPager.adapter = NewsFragmentPagerAdapter(supportFragmentManager, listOf(TopHeadlinesFragment(), EverythingFragment()))
        binding.viewPager.currentItem = 0
        binding.viewPager.setPageTransformer(true, ZoomOutPageTransformer())

        //binding.viewPager.adapter = NewsFragmentPagerAdapter(supportFragmentManager, listOf(TopHeadlinesFragment(), EverythingFragment()))

        //binding.tabs.setViewPager(binding.viewPager)

        initListeners()
    }

    private fun initListeners() {
//        binding.tabs.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {
//            override fun onPageScrolled(
//                position: Int,
//                positionOffset: Float,
//                positionOffsetPixels: Int
//            ) {
//                // TODO: 03.10.2020 Добавить
//            }
//
//            override fun onPageSelected(position: Int) {
//                // TODO: 03.10.2020 Добавить
//            }
//
//            override fun onPageScrollStateChanged(state: Int) {
//                // TODO: 03.10.2020 Добавить
//            }
//
//        })
    }
}