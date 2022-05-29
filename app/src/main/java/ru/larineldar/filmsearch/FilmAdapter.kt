package ru.larineldar.filmsearch

import android.animation.ValueAnimator
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FilmAdapter: RecyclerView.Adapter<FilmAdapter.ViewHolder>() {

    private val items = mutableListOf<Film>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val title = itemView.findViewById<TextView>(R.id.film_title)
        val description = itemView.findViewById<TextView>(R.id.film_description)
        val image = itemView.findViewById<ImageView>(R.id.film_image)

        val rating = itemView.findViewById<RatingDonutView>(R.id.rating)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_film, parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.image.setImageResource(items[position].poster)
        holder.title.text = items[position].title
        holder.description.text = items[position].description

        holder.itemView.setOnClickListener{
            (holder.itemView.context as MainActivity).launchDetailsFragment(items[position])
        }
        val progress = (items[position].rating * 10).toInt()
        val anim = ValueAnimator.ofInt(0, progress)

        anim.addUpdateListener {
            holder.rating.progress = it.animatedValue as Int
        }
        anim.duration = 500

        anim.start()
    }

    override fun getItemCount(): Int {
        return items.size
    }

    public fun addItems(items: List<Film>){
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

}