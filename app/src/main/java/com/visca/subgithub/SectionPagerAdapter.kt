package com.visca.subgithub

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class SectionPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    var username: String = ""

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        val fragment = FollowerFollowing()
        fragment.arguments = Bundle().apply {
            putInt(FollowerFollowing.ARG_SECTION_NUMBER, position + 1)
            putString(FollowerFollowing.ARG_USERNAME, username)
        }
        return fragment
    }

}