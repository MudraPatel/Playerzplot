package com.example.playerzpot.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.myapplication.utils.Constant
import com.example.playerzpot.R
import com.example.playerzpot.databinding.FragmentVideoBinding
import com.example.playerzpot.view.adapter.VideoBucketRecyclerViewAdapter
import com.example.playerzpot.viewModel.PagerViewModel
import org.json.JSONObject

class VideoFragment : Fragment() {

    private lateinit var adapterBucket: VideoBucketRecyclerViewAdapter
    private lateinit var viewModel: PagerViewModel
    private lateinit var viewBinding: FragmentVideoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewBinding = FragmentVideoBinding.inflate(inflater)

        adapterBucket = context?.let { VideoBucketRecyclerViewAdapter(it) }!!
        viewBinding.recyclerview.adapter = adapterBucket
        viewBinding.recyclerview.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        return viewBinding.root;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewBinding.recyclerview.apply {
            val itemDecoration = DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL)
            itemDecoration.setDrawable(AppCompatResources.getDrawable(context, R.drawable.divider)!!)
            addItemDecoration(itemDecoration)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PagerViewModel::class.java)

        val objBucket = JSONObject(Constant.loadJSONFromAsset(requireContext(), "bucket.json"))
        viewModel.getBucketList(objBucket, true).observe(viewLifecycleOwner, Observer { list ->
            adapterBucket.setBucketList(list)
        })

    }
}