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
import com.example.pryfinal.models.FeedDetail
import kotlinx.android.synthetic.main.item_adapter_user_friends_detail.view.*

class DetailAdapter(private val data: ArrayList<FeedDetail>) :  RecyclerView.Adapter<DetailAdapter.FeedHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailAdapter.FeedHolder {
        val inflatedView = parent.inflate(R.layout.item_adapter_user_friends_detail, false)
        return FeedHolder(inflatedView)
    }

    fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
        return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
    }

    override fun onBindViewHolder(holder: DetailAdapter.FeedHolder, position: Int) {
        val feed : FeedDetail = this.data[position]
        holder.itemView.textView.text = feed.descripcion
        Picasso.get()
            .load(feed.imagen)
            .resize(100,100)
            .centerCrop()
            .into(holder.itemView.imageView)
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