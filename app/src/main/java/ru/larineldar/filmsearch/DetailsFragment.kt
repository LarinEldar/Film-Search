package ru.larineldar.filmsearch

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton

class DetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val film = arguments?.get("film") as Film
        val image = view.findViewById<AppCompatImageView>(R.id.details_poster)
        val description = view.findViewById<TextView>(R.id.details_description)
        val title = view.findViewById<TextView>(R.id.details_title)
        val favorite = view.findViewById<FloatingActionButton>(R.id.details_fab_favorites)
        val fabShare = view.findViewById<FloatingActionButton>(R.id.details_fab)

        image.setImageResource(film.poster)
        description.text = film.description
        title.text = film.title

        favorite.setImageResource(
            if(film.isInFavorites)
                R.drawable.ic_baseline_favorite_24
            else
                R.drawable.ic_baseline_favorite_border_24
        )

        favorite.setOnClickListener{
            if (!film.isInFavorites) {
                favorite.setImageResource(R.drawable.ic_baseline_favorite_24)
                film.isInFavorites = true
            } else {
                favorite.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                film.isInFavorites = false
            }
        }

        fabShare.setOnClickListener{
            val intent = Intent(Intent.ACTION_SEND)
            intent.putExtra(Intent.EXTRA_TEXT,
                "Check out this film: ${film.title} \n\n ${film.description}")
            intent.type = "text/plain"
            startActivity(intent)
        }
    }
}