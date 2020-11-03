package com.geofferyj.jcine.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.geofferyj.jcine.misc.Movie
import com.geofferyj.jcine.R

class RVAdapter(private val movies: List<Movie>) : RecyclerView.Adapter<RVAdapter.RVViewHolder>() {


    inner class RVViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val movieImage: ImageView = item.findViewById(R.id.movie_image)
        val movieName: TextView = item.findViewById(R.id.movie_name)

        init {
            item.setOnClickListener {
                item.findNavController().navigate(R.id.action_firstPageFragment_to_detailsFragment)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.rv_item, parent, false)
        return RVViewHolder(layout)

    }

    override fun onBindViewHolder(holder: RVViewHolder, position: Int) {
        val image = movies[position].image
        val name = movies[position].name
        holder.apply {
            movieImage.setImageResource(image)
            movieName.text = name
        }
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}