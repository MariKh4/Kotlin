package com.marikh.artimages

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.art_items.view.*
import kotlinx.android.synthetic.main.activity_main.*

class ArtAdapter (val context: Context?, private val Images: ArrayList<ArtApi>):RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.art_items, parent, false)
        return ViewHolder(v)
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Picasso.get().load(Images[position].url).into(holder.itemView.Image)
    }

    class ViewHolder(v: View?):RecyclerView.ViewHolder(v!!),View.OnClickListener{
        override fun onClick(v: View?) {

        }
        init {
            itemView.setOnClickListener(this)
        }
    }

    override fun getItemCount() = Images.size

}