package ru.larineldar.filmsearch.data

import ru.larineldar.filmsearch.domain.Film

interface MainRepository {
    fun putFilmToDb(film: Film)
    fun getAllFilmsFromDB(): List<Film>
    fun clearDB()
}