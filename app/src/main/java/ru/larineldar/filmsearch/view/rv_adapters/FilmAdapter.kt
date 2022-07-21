package ru.larineldar.filmsearch.view.rv_adapters

import android.animation.ValueAnimator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.larineldar.filmsearch.view.MainActivity
import ru.larineldar.filmsearch.R
import ru.larineldar.filmsearch.databinding.ItemFilmBinding
import ru.larineldar.filmsearch.domain.Film

class FilmAdapter: RecyclerView.Adapter<FilmAdapter.ViewHolder>() {

    private val items = mutableListOf<Film>()
    private lateinit var binding : ItemFilmBinding

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val title = binding.filmTitle
        val description = binding.filmDescription
        val image = binding.filmImage

        val rating = binding.rating
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_film, parent,false)
        binding = ItemFilmBinding.bind(v)
        return ViewHolder(binding.root)
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

    fun addItems(items: List<Film>){
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

}