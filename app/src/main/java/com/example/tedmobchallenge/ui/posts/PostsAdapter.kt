package com.example.tedmobchallenge.ui.posts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.tedmobchallenge.data.Post
import com.example.tedmobchallenge.databinding.ItemPostBinding

class PostsAdapter:RecyclerView.Adapter<PostsAdapter.PostViewHolder>() {

    private val diffCallBack = PostDiffCallback()

    private val differ = AsyncListDiffer(this,diffCallBack)
    var list :List<Post>
        set(value) = differ.submitList(value)
        get() = differ.currentList
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = ItemPostBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PostViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(list[position])
    }

    class PostViewHolder(private val binding: ItemPostBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(post: Post) {
            binding.tvPostTitle.text = post.title
            binding.tvPostBody.text = post.body
        }
    }

    class PostDiffCallback : DiffUtil.ItemCallback<Post>() {
        override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem == newItem
        }
    }


}