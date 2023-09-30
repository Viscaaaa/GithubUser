package com.visca.subgithub

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.visca.subgithub.data.ItemsItem
import com.visca.subgithub.databinding.DataUserBinding

class UserAdapter(private val activity: Activity) : ListAdapter<ItemsItem, UserAdapter.MyViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.MyViewHolder {
        val binding = DataUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserAdapter.MyViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(user)
        holder.itemView.setOnClickListener{
            val moveUserDataIntent = Intent(holder.itemView.context, DetailUser::class.java)
            moveUserDataIntent.putExtra(DetailUser.USERNAME, user.login)
            activity.startActivity(moveUserDataIntent)
        }
    }

    class MyViewHolder(val binding: DataUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: ItemsItem){
            binding.tvUsername.text = "${user.login}"
            Glide.with(binding.root.context)
                .load(user.avatarUrl)
                .into(binding.imgUser)
        }

    }

    companion object{
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ItemsItem>() {
            override fun areItemsTheSame(oldItem: ItemsItem, newItem: ItemsItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: ItemsItem, newItem: ItemsItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}