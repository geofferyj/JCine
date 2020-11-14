package com.geofferyj.jcine.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.geofferyj.jcine.R
import com.geofferyj.jcine.models.MovieModel
import com.geofferyj.jcine.view.fragments.FirstPageFragmentDirections
import kotlinx.android.synthetic.main.hero_item.view.*

class HeroAdapter() : RecyclerView.Adapter<HeroAdapter.HeroViewHolder>() {

    private var onItemClickListener: ((MovieModel.Movie) -> Unit)? = null

    private val differCallback = object : DiffUtil.ItemCallback<MovieModel.Movie>() {
        override fun areItemsTheSame(
            oldItem: MovieModel.Movie,
            newItem: MovieModel.Movie
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: MovieModel.Movie,
            newItem: MovieModel.Movie
        ): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    inner class HeroViewHolder(item: View) : RecyclerView.ViewHolder(item)
    fun setOnItemClickListener(listener: (MovieModel.Movie) -> Unit) {
        onItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroViewHolder {
        return HeroViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.hero_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: HeroViewHolder, position: Int) {
        val movie = differ.currentList[position]


        holder.itemView.setOnClickListener {


        }
        val image = "https://image.tmdb.org/t/p/w500${movie.backdropPath}"
        holder.itemView.apply {

            Glide.with(holder.itemView).load(image).into(hero_image)
            setOnClickListener {
                onItemClickListener?.let { it(movie) }
            }
        }
//
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}