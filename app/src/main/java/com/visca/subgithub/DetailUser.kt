package com.visca.subgithub
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.visca.subgithub.data.ResponseDetail
import com.visca.subgithub.database.FavDao
import com.visca.subgithub.database.FavEntity
import com.visca.subgithub.databinding.ActivityDetailUserBinding

class DetailUser : AppCompatActivity() {
    private lateinit var binding: ActivityDetailUserBinding
    private val userDetailViewModel by viewModels<DetailUserViewModel>()
    private lateinit var  factory : ViewModelFactory
    private val FavViewModel : FavViewModel by viewModels {factory}
    private var username: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        userLogin = intent.getStringExtra(USERNAME).toString()
        val id = intent.getIntExtra(id, 0)
        val url = intent.getStringExtra(USER_IMAGE)
        setContentView(binding.root)

        username = intent.getStringExtra(USERNAME)


        userDetailViewModel.getDetailUser(username.toString())

        userDetailViewModel.isLoading.observe(this) {
            showLoading(it)
        }

        userDetailViewModel.user.observe(this) { user ->
            if (user != null) {
                setUserDetailData(user)
            }
        }


        factory = ViewModelFactory.getInstance(application)
        var favorite = false



        binding.fabFavorite.setOnClickListener {
//            val favEntity = FavEntity (0, userLogin, null)
//            val favEntity = userLogin?.let { FavEntity(0, userLogin, avatarUrl = null) }
            val favEntity = userLogin?.let { FavEntity(id, userLogin, url) }
            favorite = !favorite
            if (favorite){
                binding.fabFavorite?.setImageResource(R.drawable.baseline_favorite_24)
                FavViewModel.insert(favEntity as FavEntity)
            } else {
                binding.fabFavorite?.setImageResource((R.drawable.baseline_favorite_border_24))
                FavViewModel.delete(id)
            }
        }



//        val username : String = intent.getStringExtra(USERNAME)?:"Fwish"
//        val image: String = intent.getStringExtra(USER_IMAGE)?:"Fwish"
//
//        var favoritedUser: FavEntity = FavEntity().apply {
//            this.username = username
//            this.avatarUrl = image
//
//        }




//        FavViewModel.getAllFavorite().observe(this){ favoritedUser ->
//            favoritedUser.forEach{
//                if (it.username == username){
//                    FavViewModel.setThisFavorite(true)
//
//                }
//            }
//        }

    }




    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun setUserDetailData(user: ResponseDetail) {
        binding.imgDetailUser.visibility = View.VISIBLE
        binding.apply {
            Glide.with(imgDetailUser.context)
                .load(user.avatarUrl)
                .into(imgDetailUser)

            binding.tvNameDetail.visibility = View.VISIBLE
            tvNameDetail.text = "${user.name}"

            binding.tvUsernameDetail.visibility = View.VISIBLE
            tvUsernameDetail.text = "${user.login}"

            binding.tvFollower.visibility = View.VISIBLE
            tvFollower.text = "${user.followers} followers"

            binding.tvFollowing.visibility = View.VISIBLE
            tvFollowing.text = "${user.following} followings"

            binding.tabs.visibility = View.VISIBLE
        }
        val pagerAdapter = SectionPagerAdapter(this)
        pagerAdapter.username = username.toString()
        val viewPager: ViewPager2 = binding.viewPager
        viewPager.adapter = pagerAdapter
        val tabs: TabLayout = binding.tabs
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
    }

    companion object {
        var id = null
        var userLogin = String()
        var USER_IMAGE = "image"
        var USERNAME = "username"
        private val TAB_TITLES = intArrayOf(
            R.string.follower,
            R.string.following
        )
    }
}