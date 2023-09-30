package com.visca.subgithub

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.visca.subgithub.data.ResponseDetail
import com.visca.subgithub.databinding.DataUserBinding

class FollowerFollowingAdapter : ListAdapter<ResponseDetail, FollowerFollowingAdapter.MyViewHolder>(DIFF_CALLBACK) {

    class MyViewHolder(private val binding: DataUserBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(user: ResponseDetail) {
            fun ImageView.loadImage(url: String) {
                Glide.with(this.context)
                    .load(url)
                    .centerCrop()
                    .into(this)
            }

            binding.apply {
                tvUsername.text = user.login
                imgUser.loadImage(
                    url = user.avatarUrl
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = DataUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: MyViewHolder,
        position: Int
    ) {
        val user = getItem(position)
        holder.bind(user)
    }

    companion object{
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ResponseDetail>() {
            override fun areItemsTheSame(
                oldItem: ResponseDetail,
                newItem: ResponseDetail
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: ResponseDetail,
                newItem: ResponseDetail
            ): Boolean {
                return oldItem == newItem
            }

        }
    }

}