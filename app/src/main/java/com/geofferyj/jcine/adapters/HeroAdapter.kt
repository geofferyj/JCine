package com.geofferyj.jcine.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.geofferyj.jcine.R
import kotlinx.android.synthetic.main.hero_item.view.*

class HeroAdapter(private val images: List<Int>): RecyclerView.Adapter<HeroAdapter.HeroViewHolder>() {

    inner class HeroViewHolder(item: View): RecyclerView.ViewHolder(item){
        private val heroImage: ImageView = item.findViewById(R.id.hero_image)

        init {
            heroImage.setOnClickListener{
                val position = adapterPosition
                Toast.makeText(
                    item.context,
                    "Item at position $position was clicked",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroViewHolder {
        return HeroViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.hero_item, parent, false))
    }

    override fun onBindViewHolder(holder: HeroViewHolder, position: Int) {
        val image = images[position]
        holder.itemView.hero_image.setImageResource(image)
    }

    override fun getItemCount(): Int {
        return 5
    }
}