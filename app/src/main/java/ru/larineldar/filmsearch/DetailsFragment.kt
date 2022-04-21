package ru.larineldar.filmsearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.Fragment

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

        image.setImageResource(film.poster)
        description.text = film.description
        title.text = film.title
    }
}