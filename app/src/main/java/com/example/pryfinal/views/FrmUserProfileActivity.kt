package com.example.pryfinal.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.example.pryfinal.R
import com.example.pryfinal.network.Repository
import com.example.pryfinal.network.UserResponse
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_frm_user_profile.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher
import retrofit2.HttpException

class FrmUserProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_frm_user_profile)
        Lyt_Usuario_VerPost.setOnClickListener{
            val intent = Intent(this, FrmUserPostsActivity::class.java)
            startActivity(intent)
        }
        callService()
    }
    private fun callService() {
        val service = Repository.RetrofitRepository.getService()

        CoroutineScope(Dispatchers.IO).launch {
            val response = service.getProfile()

            withContext(Dispatchers.Main) {
                try {
                    if (response.isSuccessful) {

                        val user: UserResponse? = response.body()
                        if (user != null) {
                            txt_Usuario_Ver_Job.text = user.occupation
                            txt_Usuario_Ver_Mail.text = user.email
                            txt_Usuario_Ver_Origin_City.text = user.location
                            txt_Usuario_Ver_YearsOld.text = user.age
                            txt_Usuario_Ver_Amigos.text = user.social.friends.toString()
                            txt_Usuario_Ver_Historias.text = user.social.posts .toString()
                            txt_Usuario_Ver_NroLikes.text = user.social.likes.toString()
                            txt_Usuario_Ver_Subidas.text = user.social.shares.toString()

                            Toast.makeText(
                                this@FrmUserProfileActivity,
                                "Usuario: ${user.name} tiene ${user.social.friends} amigos",
                                Toast.LENGTH_LONG
                            ).show()
                            val im = findViewById<ImageView>(R.id.Ver_Profile_image)
                            Picasso.get()
                                .load(user.image)
                                .resize(100,100)
                                .centerCrop()
                                .into(im)
                        }
                    } else {
                        Toast.makeText(
                            this@FrmUserProfileActivity,
                            "Error ${response.code()}",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                } catch (e: HttpException) {
                    Toast.makeText(
                        this@FrmUserProfileActivity,
                        "Error ${e.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }
}