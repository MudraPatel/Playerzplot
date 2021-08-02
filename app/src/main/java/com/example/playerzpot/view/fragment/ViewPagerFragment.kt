package com.example.playerzpot.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.myapplication.utils.Constant.Companion.loadJSONFromAsset
import com.example.playerzpot.R
import com.example.playerzpot.databinding.FragmentViewPagerBinding
import com.example.playerzpot.view.adapter.BucketRecyclerViewAdapter
import com.example.playerzpot.view.adapter.StoryRecyclerViewAdapter
import com.example.playerzpot.viewModel.PagerViewModel
import org.json.JSONObject

class ViewPagerFragment : Fragment() {

    companion object {
        const val ARG_POSITION = "position"
        const val ARG_TITLE = "title"
        const val ARG_FRAGMENT = "fragment"

        fun getInstance(position: Int, tabTitle: String, fragmentName: String): Fragment {
            val ViewPagerFragment = ViewPagerFragment()
            val bundle = Bundle()
            bundle.putInt(ARG_POSITION, position)
            bundle.putString(ARG_TITLE, tabTitle)
            bundle.putString(ARG_FRAGMENT, fragmentName)
            ViewPagerFragment.arguments = bundle
            return ViewPagerFragment
        }
    }

    private lateinit var adapterStory: StoryRecyclerViewAdapter
    private lateinit var adapterBucket: BucketRecyclerViewAdapter
    private lateinit var viewModel: PagerViewModel

    private lateinit var viewBinding: FragmentViewPagerBinding

    var position: Int = 0;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         viewBinding = FragmentViewPagerBinding.inflate(inflater)

        adapterStory = StoryRecyclerViewAdapter()
        viewBinding.recyclerviewStory.adapter = adapterStory
        viewBinding.recyclerviewStory.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        adapterBucket = context?.let { BucketRecyclerViewAdapter(it) }!!
        viewBinding.recyclerviewBucket.adapter = adapterBucket
        viewBinding.recyclerviewBucket.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        return viewBinding.root;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        position = requireArguments().getInt(ARG_POSITION)
        val title = requireArguments().getString(ARG_TITLE)

        viewBinding.recyclerviewBucket.apply {
            val itemDecoration = DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL)
            itemDecoration.setDrawable(getDrawable(context, R.drawable.divider)!!)
            addItemDecoration(itemDecoration)
        }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PagerViewModel::class.java)

        val obj = JSONObject(loadJSONFromAsset(requireContext(), "story_bucket.json"))
        viewModel.getStoryList(obj).observe(viewLifecycleOwner, Observer { list ->
            //set data
            adapterStory.setStoryList(list)
        })

        val objBucket = JSONObject(loadJSONFromAsset(requireContext(), "bucket.json"))
        viewModel.getBucketList(objBucket, false).observe(viewLifecycleOwner, Observer { list ->

            //set data
            adapterBucket.setBucketList(list)
        })

    }
}
