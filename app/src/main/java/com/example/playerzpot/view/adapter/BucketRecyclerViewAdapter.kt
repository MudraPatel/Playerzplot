package com.example.playerzpot.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.demo.myapplication.utils.Constant
import com.example.playerzpot.R
import com.example.playerzpot.databinding.ListBucketBinding
import com.example.playerzpot.model.BucketModel

class BucketRecyclerViewAdapter(var context: Context) : RecyclerView.Adapter<BucketRecyclerViewAdapter.ViewHolder>() {
    private var values = emptyList<BucketModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding  = ListBucketBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.apply {
            viewBinding.apply {
                tvTitle.text = item.title
                tvViewed.text = item.viewed
                constraintLayout.setOnClickListener {
                    val bundle = bundleOf(
                        Constant.BUCKET_ID_PARAMETER to item.id,
                        Constant.COUNT_PARAMETER to values.size
                    )
                    Navigation.findNavController(it).navigate(R.id.detailFragment, bundle)
                }
            }
        }

    }

    internal fun setBucketList(values: List<BucketModel>) {
        this.values = values
    }


    override fun getItemCount(): Int = values.size

    inner class ViewHolder(val viewBinding: ListBucketBinding) : RecyclerView.ViewHolder(viewBinding.root)

}