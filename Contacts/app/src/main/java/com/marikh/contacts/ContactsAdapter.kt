package com.marikh.contacts

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.contact_item.*


class ContactsAdapter(private val context: Context, private val contactsList: List<Contact>
) : RecyclerView.Adapter<ContactsAdapter.ContactViewHolder>() {


    class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val contactName : TextView = itemView.findViewById(R.id.contactName)
        private val contactPhone : TextView = itemView.findViewById(R.id.contactPhone)
        private val contactImg : ImageView = itemView.findViewById(R.id.iv_image)
        private val imgsList : Array<Int> = arrayOf(R.drawable.user1, R.drawable.user2,
            R.drawable.user3, R.drawable.user4, R.drawable.user5)

        fun bind(index: Int, list: List<Contact>) {
            val rndImgIndex : Int = (0..4).random()
            contactImg.setImageResource(imgsList[rndImgIndex])
            contactName.text = list[index].contactName
            contactPhone.text = list[index].contactPhone
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(context)
        return ContactViewHolder(inflater.inflate(R.layout.contact_item, parent, false))
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bind(position, contactsList)
    }

    override fun getItemCount(): Int {
        return contactsList.size
    }

}
