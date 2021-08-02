package com.example.playerzpot.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.demo.myapplication.utils.Constant
import com.demo.myapplication.utils.Constant.Companion.BUCKET_ID_PARAMETER
import com.demo.myapplication.utils.Constant.Companion.COUNT_PARAMETER
import com.example.playerzpot.R
import com.example.playerzpot.databinding.FragmentDetailBinding
import com.example.playerzpot.viewModel.PagerViewModel
import org.json.JSONObject


class DetailFragment : Fragment() {

    private lateinit var viewModel: PagerViewModel
    private lateinit var viewBinding: FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewBinding =  FragmentDetailBinding.inflate(inflater)

        viewBinding.shareButton.setOnClickListener {
            val intent= Intent()
            intent.action=Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT,"Hey Check out this Great app:")
            intent.type="text/plain"
            startActivity(Intent.createChooser(intent,"Share To:"))
        }
        return viewBinding.root;
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val bucketId = arguments?.getInt(BUCKET_ID_PARAMETER)
        val count = arguments?.getInt(COUNT_PARAMETER)
        viewModel = ViewModelProvider(this).get(PagerViewModel::class.java)
        val objBucket = JSONObject(Constant.loadJSONFromAsset(requireContext(), "bucket.json"))

        bucketId?.let {  viewModel.getBucketDetails(objBucket, it) }

        viewModel.bucketDetailLiveData.observe(viewLifecycleOwner, Observer { details ->
            viewBinding.tvTitle.text = details.title
            viewBinding.tvDetails.text = details.details
            viewBinding.tvHighlight.text = getString(R.string.highlight_text, details.viewed, (details.id + 1).toString(), count.toString())
        })

    }
}