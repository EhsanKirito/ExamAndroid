package com.example.kointest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kointest.Network.MovieResult

class MovieAdaptor : ListAdapter<MovieResult, MovieAdaptor.PagingViewHolder>(ToDoCallBack()) {
    class PagingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val textName:TextView = itemView.findViewById(R.id.name)
        val textTitle:TextView = itemView.findViewById(R.id.title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return PagingViewHolder(view)
    }

    override fun onBindViewHolder(holder: PagingViewHolder, position: Int) {
        val item: MovieResult = getItem(position)
        holder.textName.setText(item.title)
//        holder.textTitle.setText(item.results)
//        Glide.with(holder.itemView.context)
//            .load("https://image.tmdb.orgt/p/w/500" + item.poster_path)
//            .into(holder.imageView)

    }

}

class ToDoCallBack : DiffUtil.ItemCallback<MovieResult>() {
    override fun areItemsTheSame(oldItem: MovieResult, newItem: MovieResult): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: MovieResult, newItem: MovieResult): Boolean {
        return oldItem.id == newItem.id
    }

}
