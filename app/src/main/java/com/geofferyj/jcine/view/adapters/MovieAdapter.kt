package com.geofferyj.jcine.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.geofferyj.jcine.R
import com.geofferyj.jcine.models.MovieModel
import kotlinx.android.synthetic.main.rv_item.view.*

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {


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




    inner class MovieViewHolder(item: View) : RecyclerView.ViewHolder(item)

    fun setOnItemClickListener(listener: (MovieModel.Movie) -> Unit) {
        onItemClickListener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.rv_item, parent, false)
        return MovieViewHolder(layout)

    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = differ.currentList[position]


        val image = "https://image.tmdb.org/t/p/w500${movie.posterPath}"
        val name = movie.title
        holder.itemView.apply {
            movie_name.text = name
            Glide.with(this).load(image).into(movie_image)
            setOnClickListener {
                onItemClickListener?.let { it(movie) }
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}