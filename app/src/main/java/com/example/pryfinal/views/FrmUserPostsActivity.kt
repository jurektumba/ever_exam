package com.example.pryfinal.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pryfinal.R
import com.example.pryfinal.adapters.UserPostsAdapter
import com.example.pryfinal.models.FeedPosts
import com.example.pryfinal.network.Repository
import kotlinx.android.synthetic.main.activity_frm_user_posts.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class FrmUserPostsActivity : AppCompatActivity() {
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: UserPostsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_frm_user_posts)
        callServiceList()
    }
    private fun callServiceList() {
        val service = Repository.RetrofitRepository.getService()
        CoroutineScope(Dispatchers.IO).launch {
            val response = service.getPosts()

            withContext(Dispatchers.Main) {

                try {
                    if (response.isSuccessful) {
                        val feedList = ArrayList<FeedPosts>()
                        val lista = response.body()
                        if (lista != null) {
                            for (item in lista ){
                                val usern = item.username
                                val likes = item.likes.toString()
                                val useri = item.user_image
                                val imag = item.image
                                val desc = item.body
                                val it: FeedPosts = FeedPosts(usern,likes,useri,imag,desc)
                                feedList.add(it)
                            }
                            adapter = UserPostsAdapter(feedList)
                            linearLayoutManager = LinearLayoutManager(this@FrmUserPostsActivity)
                            recyclerUserPosts.layoutManager= linearLayoutManager
                            recyclerUserPosts.adapter = adapter
                        }
                        Toast.makeText(
                            this@FrmUserPostsActivity,
                            "Error ${response.code()}",
                            Toast.LENGTH_LONG
                        ).show()
                    } else {
                        Toast.makeText(
                            this@FrmUserPostsActivity,
                            "Error ${response.code()}",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                } catch (e: HttpException) {
                    Toast.makeText(
                        this@FrmUserPostsActivity,
                        "Error ${e.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }
}