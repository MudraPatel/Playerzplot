package com.example.playerzpot.view.fragment

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.demo.myapplication.utils.Constant
import com.example.playerzpot.BaseFragment
import com.example.playerzpot.R
import com.example.playerzpot.view.adapter.TabAdapter
import com.example.playerzpot.viewModel.DiscoverViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import org.json.JSONObject

class DiscoverFragment : BaseFragment() {

    override fun layoutId(): Int  = R.layout.fragment_discover
    override fun viewPagerId(): Int =  R.id.pager;
    override var fragmentName: String = Constant.DISCOVER_FRAGMENT
//    override fun taLayoutId(): Int =  R.id.tabs;

    private lateinit var viewModel: DiscoverViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun setTabList(adapterViewPager: TabAdapter, tabLayout: TabLayout, viewPager2: ViewPager2)
    {
        viewModel = ViewModelProvider(this).get(DiscoverViewModel::class.java)

        val obj = JSONObject(Constant.loadJSONFromAsset(requireContext(), "discover_tab.json"))

        viewModel.getTabList(obj).observe(viewLifecycleOwner, Observer { list ->

            adapterViewPager.setTabList(list.size, list)
            // Connect TabLayout and ViewPager2 here
            TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
                //To get the first name of  tab
                tab.text = list[position].title//.substringBefore(' ')
            }.attach()
        })
    }
}