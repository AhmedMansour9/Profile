package com.profile.Activites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.profile.R
import kotlinx.android.synthetic.main.activity_full_screen.*
import kotlinx.android.synthetic.main.item_profile.view.*

class FullScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_screen)
        getData()
    }

    private fun getData(){
        var Image=intent.getStringExtra("details")
        setFullScreenImage(Image)
    }

    private fun setFullScreenImage(img:String) {
        Glide.with(applicationContext)
            .load(img).into(img_fullscreen)
    }
}
