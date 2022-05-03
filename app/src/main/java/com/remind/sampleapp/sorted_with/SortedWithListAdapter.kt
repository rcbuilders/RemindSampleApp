package com.remind.sampleapp.sorted_with

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.remind.sampleapp.databinding.ListItemTextBinding

class SortedWithListAdapter()
    : ListAdapter<UserScore, SortedWithListAdapter.ViewHolder>(
    object : DiffUtil.ItemCallback<UserScore>() {
        override fun areItemsTheSame(oldItem: UserScore, newItem: UserScore): Boolean {
            return true
        }

        override fun areContentsTheSame(oldItem: UserScore, newItem: UserScore): Boolean {
            return true
        }

    }
) {
    inner class ViewHolder(private val binding: ListItemTextBinding): RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: UserScore) {
            binding.item = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ListItemTextBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

}