package com.geofferyj.jcine.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.geofferyj.jcine.R
import com.geofferyj.jcine.models.MovieDetails
import com.geofferyj.jcine.models.MovieModel
import kotlinx.android.synthetic.main.rv_item.view.*

class CastAdapter : RecyclerView.Adapter<CastAdapter.CastViewHolder>() {


    private val differCallback = object : DiffUtil.ItemCallback<MovieDetails.Credits.Cast>() {
        override fun areItemsTheSame(
            oldItem: MovieDetails.Credits.Cast,
            newItem: MovieDetails.Credits.Cast
        ): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(
            oldItem: MovieDetails.Credits.Cast,
            newItem: MovieDetails.Credits.Cast
        ): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)




    inner class CastViewHolder(item: View) : RecyclerView.ViewHolder(item)



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.rv_item, parent, false)
        return CastViewHolder(layout)

    }

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        val cast = differ.currentList[position]


        val image = "https://image.tmdb.org/t/p/w500${cast.profilePath}"
        val name = cast.name
        holder.itemView.apply {
            movie_name.text = name
            Glide.with(this).load(image).into(movie_image)
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}