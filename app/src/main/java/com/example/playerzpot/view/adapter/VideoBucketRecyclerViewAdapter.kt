package com.example.playerzpot.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.playerzpot.R
import com.example.playerzpot.databinding.ListVideoBinding
import com.example.playerzpot.model.BucketModel

class VideoBucketRecyclerViewAdapter(var context: Context
) : RecyclerView.Adapter<VideoBucketRecyclerViewAdapter.ViewHolder>() {
    private var values = emptyList<BucketModel>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding  = ListVideoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.apply {
            viewBinding.tvTitle.text = item.title
            viewBinding.tvViewed.text = item.viewed
            viewBinding.tvDuration.text = item.videoTime
            viewBinding.ivFavorite.setOnClickListener {
                if(item.isFavorite){
                    viewBinding.ivFavorite.setImageResource(R.drawable.ic_bookmark_unfilled)
                    item.isFavorite = false
                }else{
                    viewBinding.ivFavorite.setImageResource(R.drawable.ic_bookmark_filled)
                    item.isFavorite = true
                }
            }
        }
    }

    internal fun setBucketList(values: List<BucketModel>) {
        this.values = values
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(val viewBinding: ListVideoBinding) : RecyclerView.ViewHolder(viewBinding.root)
}