package ru.larineldar.filmsearch.view.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.larineldar.filmsearch.domain.Film
import ru.larineldar.filmsearch.R
import ru.larineldar.filmsearch.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val film = arguments?.get("film") as Film
        val favorite = binding.detailsFabFavorites

        binding.detailsPoster.setImageResource(film.poster)
        binding.detailsDescription.text = film.description
        binding.detailsTitle.text = film.title

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

        binding.detailsFab.setOnClickListener{
            val intent = Intent(Intent.ACTION_SEND)
            intent.putExtra(Intent.EXTRA_TEXT,
                "Check out this film: ${film.title} \n\n ${film.description}")
            intent.type = "text/plain"
            startActivity(intent)
        }
    }
}