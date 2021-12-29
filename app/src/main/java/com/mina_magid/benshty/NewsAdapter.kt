package com.mina_magid.benshty

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class NewsAdapter(
    private val newsList: ArrayList<News>,
) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.username.text = newsList[position].name
        holder.am.text = newsList[position].mount
        holder.price.text = newsList[position].price

    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var username = itemView.findViewById<TextView>(R.id.name)!!
        val price = itemView.findViewById<TextView>(R.id.price)!!
        val am = itemView.findViewById<TextView>(R.id.am)!!

    }
}