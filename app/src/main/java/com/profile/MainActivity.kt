package com.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.profile.Adapter.Profile_Adapter
import com.profile.Model.Home_Response
import com.profile.Model.Profile_Response
import com.profile.ViewModel.ImagesProfile_ViewModel
import com.profile.ViewModel.Profile_ViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() , SwipeRefreshLayout.OnRefreshListener{


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        SwipRefresh()


    }

    fun SwipRefresh(){
        SwipHome.setOnRefreshListener(this)
        SwipHome.isRefreshing=true
        SwipHome.setColorSchemeResources(
            R.color.colorPrimary,
            android.R.color.holo_green_dark,
            android.R.color.holo_orange_dark,
            android.R.color.holo_blue_dark
        )
        SwipHome.post(Runnable {
        getProfile()
        getImages()
        })
    }

    fun getProfile(){
        val profileUser = ViewModelProvider.NewInstanceFactory().create(Profile_ViewModel::class.java)
        this.applicationContext?.let {
            profileUser.getProfile(it).observe(this, Observer<Profile_Response> { profiolemodel ->
                SwipHome.isRefreshing=false
                if(profiolemodel!=null) {
                    setDataUser(profiolemodel.data)
                }

            })
        }
    }

    fun getImages(){
        val profileImages = ViewModelProvider.NewInstanceFactory().create(ImagesProfile_ViewModel::class.java)
        this.applicationContext?.let {
            profileImages.getImages(it).observe(this, Observer<Home_Response> { profiolemodel ->
                SwipHome.isRefreshing=false
                if(profiolemodel!=null) {
                    refreshUIWith(profiolemodel.data)
                }

            })
        }
    }

    private fun setDataUser(profileuser:Profile_Response.Data){
        T_Name.text=profileuser.fullName
        T_Address.text=profileuser.location
        T_information.text=profileuser.bio
        T_posts.text=profileuser.counts.posts.toString()
        T_follwers.text=profileuser.counts.followers.toString()
        T_follwing.text=profileuser.counts.following.toString()
        Glide.with(applicationContext)
            .load(profileuser.profilePicture).into(img_profile)

    }
    override fun onRefresh() {
        getProfile()
    }

    private fun refreshUIWith(teamss: List<Home_Response.Data>){
        SwipHome.isRefreshing=false
        val listAdapter =
            Profile_Adapter(applicationContext, teamss)
        Recycle_Home.layoutManager = GridLayoutManager(
            this.applicationContext,
            3,
            GridLayoutManager.VERTICAL,
            false
        )
        Recycle_Home.adapter=listAdapter
    }


}
