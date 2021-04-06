package com.marikh.artimages

import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.StringRequestListener
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray


class MainActivity : AppCompatActivity() {

    val imageList = ArrayList<ArtApi>()

    private lateinit var imagesRV: RecyclerView
    private lateinit var SearchET: EditText
    private lateinit var searchBtn: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imagesRV = findViewById(R.id.imegasRecyclerView)
        SearchET = findViewById(R.id.SearchET)
        searchBtn = findViewById(R.id.searchBtn)

        imagesRV.layoutManager = LinearLayoutManager(this)

        val url = "https://picsum.photos/v2/list"
        search(url)

        searchBtn.setOnClickListener{

            val number = SearchET.text.toString()
            val url = "https://picsum.photos/v2/list?page=$number"
            progressBar.visibility = View.INVISIBLE
            search(url)
        }
    }

    private fun search(url: String) {
        imageList.clear()

        AndroidNetworking.initialize(this)

        AndroidNetworking.get(url)
            .setPriority(Priority.HIGH)
            .build()
            .getAsString(object : StringRequestListener{
                override fun onResponse(response: String?) {

                    val image = JSONArray(response)
                    for (i in 0 until image.length()){
                        progressBar.visibility = View.VISIBLE
                        progressBar.max = image.length()
                        val currentProgress = imageList.toString().length
                        val list = image.getJSONObject(i).getString("download_url")
                        imageList.add(
                            ArtApi(list.toString())
                        )
                        ObjectAnimator.ofInt(progressBar, "progress", currentProgress, progressBar.max)
                                .start()
                    }
                    imagesRV.adapter = ArtAdapter(this@MainActivity, imageList)
                }
                override fun onError(anError: ANError?) {
                    TODO("Not yet implemented")
                }

            })
    }
}