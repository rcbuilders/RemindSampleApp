package com.remind.sampleapp.lorem_picsum.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.remind.sampleapp.databinding.ListItemImageBinding
import com.remind.sampleapp.lorem_picsum.model.ImageInfo

interface OnImageItemClickListener<T> {
    fun onImageItemClicked(item: T?)
}

class LoremPicsumListAdapter(private val listener: OnImageItemClickListener<ImageInfo>?):
    PagingDataAdapter<ImageInfo, LoremPicsumListAdapter.ImageViewHolder>(
        object : DiffUtil.ItemCallback<ImageInfo>() {
            override fun areItemsTheSame(oldItem: ImageInfo, newItem: ImageInfo): Boolean {
                return oldItem.id == newItem.id && oldItem.author == newItem.author
            }

            override fun areContentsTheSame(oldItem: ImageInfo, newItem: ImageInfo): Boolean {
                return oldItem.id == newItem.id && oldItem.equals(newItem)
            }

        }
    ) {

    override fun onBindViewHolder(holder: LoremPicsumListAdapter.ImageViewHolder, position: Int) {
        val item = getItem(position)?: return
        holder.onBind(item)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LoremPicsumListAdapter.ImageViewHolder {
        return ImageViewHolder(binding = ListItemImageBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        )
    }

    inner class ImageViewHolder(private val binding: ListItemImageBinding):
        RecyclerView.ViewHolder(binding.root) {

            init {
                binding.root.setOnClickListener {
                    listener?.onImageItemClicked(binding.item)
                }
            }

            fun onBind(item: ImageInfo) {
                binding.item = item
            }
    }
}