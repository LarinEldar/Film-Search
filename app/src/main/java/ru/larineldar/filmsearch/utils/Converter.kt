package ru.larineldar.filmsearch.utils

import ru.larineldar.filmsearch.data.entity.TmdbFilm
import ru.larineldar.filmsearch.domain.Film

object Converter {
    fun converterApiListToDtoList(list: List<TmdbFilm>?): List<Film> {
        val result = mutableListOf<Film>()
        list?.forEach{
            result.add(Film(
                title = it.title,
                poster = it.poster_path,
                description = it.overview,
                rating = it.vote_average,
                isInFavorites = false
            ))
        }
        return result
    }
}