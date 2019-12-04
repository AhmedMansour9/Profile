package com.profile.Adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.profile.Activites.FullScreen
import com.profile.Model.Home_Response
import com.profile.Model.Profile_Response
import com.profile.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_profile.view.*
import kotlinx.android.synthetic.main.item_profile.view.img_profile

class Profile_Adapter  (context: Context, val ListTeams: List<Home_Response.Data>)
    : RecyclerView.Adapter<Profile_Adapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Profile_Adapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_profile, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: Profile_Adapter.ViewHolder, position: Int) {
        holder.bindItems(ListTeams.get(position))

    }

    override fun getItemCount(): Int {
        return ListTeams.size
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val context: Context = itemView.context

        fun bindItems(dataModel: Home_Response.Data) {
            Glide.with(context.applicationContext)
                .load(dataModel.image).into(itemView.img_profile)
            openDetailsTeam(dataModel .image)
        }

        fun openDetailsTeam(dataModel: String){
            itemView.setOnClickListener(){
                val intent = Intent(context, FullScreen::class.java)
                intent.putExtra("details",dataModel)
                context.startActivity(intent)
            }

        }

    }

}
