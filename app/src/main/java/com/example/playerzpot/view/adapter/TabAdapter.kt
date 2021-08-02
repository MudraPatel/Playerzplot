package com.example.playerzpot.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.playerzpot.model.TabModel
import com.example.playerzpot.view.fragment.ViewPagerFragment


class TabAdapter(activity: FragmentActivity, var mNumOfTabs: Int, var tabTitleList: ArrayList<TabModel>, var fragmentName  : String) :
    FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        return mNumOfTabs
    }

    override fun createFragment(position: Int): Fragment {
        return ViewPagerFragment.getInstance(position, tabTitleList.get(position).title, fragmentName)

    }

    internal fun setTabList(size : Int, list: ArrayList<TabModel>) {
        tabTitleList = list
        mNumOfTabs = size
        notifyDataSetChanged()
    }


}