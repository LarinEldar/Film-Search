package ru.larineldar.filmsearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import org.w3c.dom.Text

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val film = intent.extras?.get("film") as Film

        val image = findViewById<AppCompatImageView>(R.id.details_poster)
        val description = findViewById<TextView>(R.id.details_description)
        val title = findViewById<TextView>(R.id.details_title)

        image.setImageResource(film.poster)
        description.text = film.description
        title.text = film.title
    }
}