package com.daro.cleanarchitecture.posts.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.daro.cleanarchitecture.databinding.LayoutItemPostBinding
import com.daro.cleanarchitecture.posts.entities.PostViewModel

class PostsAdapter(private val clickListener: OnItemClickListener) :
    ListAdapter<PostViewModel, PostsAdapter.PostsViewHolder>(PostsDiffUtil()) {

    class PostsDiffUtil : DiffUtil.ItemCallback<PostViewModel>() {
        override fun areItemsTheSame(oldItem: PostViewModel, newItem: PostViewModel) =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: PostViewModel, newItem: PostViewModel) =
            oldItem.id == newItem.id && oldItem.title == newItem.title && oldItem.body == newItem.body
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        val from = LayoutInflater.from(parent.context)
        val itemBinding = LayoutItemPostBinding.inflate(from, parent, false)
        return PostsViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class PostsViewHolder(private val binding: LayoutItemPostBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PostViewModel) {
            binding.post = item
            binding.clickListener = View.OnClickListener { clickListener.onItemClicked(item) }
        }

    }
}