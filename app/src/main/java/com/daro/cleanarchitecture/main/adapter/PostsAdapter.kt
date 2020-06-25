package com.daro.cleanarchitecture.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.daro.cleanarchitecture.databinding.LayoutItemPostBinding
import com.daro.cleanarchitecture.main.entities.PostViewModel

class PostsAdapter : ListAdapter<PostViewModel, PostsAdapter.PostsViewHolder>(PostsDiffUtil()) {

    class PostsDiffUtil : DiffUtil.ItemCallback<PostViewModel>() {
        override fun areItemsTheSame(oldItem: PostViewModel, newItem: PostViewModel) =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: PostViewModel, newItem: PostViewModel) =
            oldItem.id == newItem.id
                    && oldItem.title == newItem.title
                    && oldItem.body == newItem.body

    }

    inner class PostsViewHolder(private val binding: LayoutItemPostBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PostViewModel) {
            binding.title.text = item.title
            binding.body.text = item.body
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        val from = LayoutInflater.from(parent.context)
        val itemBinding = LayoutItemPostBinding.inflate(from, parent, false)
        return PostsViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}