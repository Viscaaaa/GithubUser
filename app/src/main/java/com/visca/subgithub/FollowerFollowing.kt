package com.visca.subgithub

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.visca.subgithub.databinding.FragmentFollowerFollowingBinding


class FollowerFollowing : Fragment() {
    private val userDetailViewModel by viewModels<DetailUserViewModel>()

    private lateinit var binding: FragmentFollowerFollowingBinding
    private var position: Int? = null
    private var username: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFollowerFollowingBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            position = it.getInt(ARG_SECTION_NUMBER)
            username = it.getString(ARG_USERNAME)
            when(position){
                1 -> userDetailViewModel.getFollowerUser(username.toString())
                2 -> userDetailViewModel.getFollowingUser(username.toString())
            }
        }
        userDetailViewModel.isLoading.observe(viewLifecycleOwner){
            showLoading(it)
        }

        userDetailViewModel.userFollower.observe(viewLifecycleOwner){
            val adapter = FollowerFollowingAdapter()
            adapter.submitList(it)
            binding.rvFollowingFollower.layoutManager = LinearLayoutManager(requireContext())
            binding.rvFollowingFollower.setHasFixedSize(true)
            binding.rvFollowingFollower.adapter = adapter
        }

        userDetailViewModel.userFollowing.observe(viewLifecycleOwner){
            val adapter = FollowerFollowingAdapter()
            adapter.submitList(it)
            binding.rvFollowingFollower.layoutManager = LinearLayoutManager(requireContext())
            binding.rvFollowingFollower.setHasFixedSize(true)
            binding.rvFollowingFollower.adapter = adapter
        }
    }

    companion object {
        const val ARG_SECTION_NUMBER = "section_number"
        const val ARG_USERNAME= "username"
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}