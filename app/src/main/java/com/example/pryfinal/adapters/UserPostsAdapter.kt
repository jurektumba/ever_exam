package com.example.pryfinal.adapters

import androidx.recyclerview.widget.RecyclerView
import com.example.pryfinal.models.FeedPosts
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import com.squareup.picasso.Picasso
import com.example.pryfinal.R
import kotlinx.android.synthetic.main.item_adapter_userposts.view.*

class UserPostsAdapter(private val data: ArrayList<FeedPosts>) :  RecyclerView.Adapter<UserPostsAdapter.FeedHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserPostsAdapter.FeedHolder {
        val inflatedView = parent.inflate(R.layout.item_adapter_userposts, false)
        return FeedHolder(inflatedView)
    }

    fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
        return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
    }

    override fun onBindViewHolder(holder: UserPostsAdapter.FeedHolder, position: Int) {
        val feed : FeedPosts = this.data[position]
        holder.itemView.item_detail_username.text = feed.username
        holder.itemView.item_detail_description.text = feed.descripcion
        holder.itemView.item_detail_username_likes.text = feed.likes
        Picasso.get()
            .load(feed.userImage)
            .resize(100,100)
            .centerCrop()
            .into(holder.itemView.item_detail_image)
        Picasso.get()
            .load(feed.image)
            .resize(400,161)
            .centerCrop()
            .into(holder.itemView.item_detail_image_post)
    }


    override fun getItemCount(): Int {
        return data.size
    }

    class FeedHolder(v: View): RecyclerView.ViewHolder(v), View.OnClickListener {
        private var view: View = v
        private var feed : FeedPosts? = null

        init {
            v.setOnClickListener(this)
        }
        override fun onClick(v: View?) {
            if (v != null) {
                Toast.makeText(v.context, "Item", Toast.LENGTH_SHORT).show()
            }
        }

    }
}