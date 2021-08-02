package com.example.playerzpot.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.playerzpot.databinding.ListNewsLayoutBinding
import com.example.playerzpot.model.StoryModel

class StoryRecyclerViewAdapter() : RecyclerView.Adapter<StoryRecyclerViewAdapter.ViewHolder>() {
    private var values = emptyList<StoryModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding  = ListNewsLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.apply {
            viewBinding.apply {
                tvTeam1Name.text = item.team1Name
                tvTeam2Name.text = item.team2Name
                tvTeam1Score.text = item.team1Score
                tvTeam2Score.text = item.team2Score
                tvLiveMsg.setSelected(true)
            }
        }
    }

    internal fun setStoryList(values: List<StoryModel>) {
        this.values = values
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(val viewBinding: ListNewsLayoutBinding) : RecyclerView.ViewHolder(viewBinding.root)
}