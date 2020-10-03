package com.axproject.newsapplication.utils.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.astuetz.PagerSlidingTabStrip
import com.axproject.newsapplication.R

class NewsFragmentPagerAdapter(fragmentManager: FragmentManager, private val fragmentList : List<Fragment>)
    : FragmentPagerAdapter(fragmentManager)
//    , PagerSlidingTabStrip.IconTabProvider
{

    private val tabIcons = listOf(R.drawable.ic_launcher_background, R.drawable.ic_launcher_background)

    override fun getCount(): Int {
        return fragmentList.size
    }

    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

//    override fun getPageIconResId(position: Int): Int {
//        return tabIcons[position]
//    }

}