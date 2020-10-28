package com.geofferyj.jcine

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.hero_view.view.*

class HeroAdapter(private val images: List<Int>): RecyclerView.Adapter<HeroAdapter.HeroViewHolder>() {

    inner class HeroViewHolder(item: View): RecyclerView.ViewHolder(item)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroAdapter.HeroViewHolder {
        return HeroViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.hero_view, parent, false))
    }

    override fun onBindViewHolder(holder: HeroAdapter.HeroViewHolder, position: Int) {
        val image = images[position]
        holder.itemView.hero_image.setImageResource(image)
    }

    override fun getItemCount(): Int {
        return 5
    }
}