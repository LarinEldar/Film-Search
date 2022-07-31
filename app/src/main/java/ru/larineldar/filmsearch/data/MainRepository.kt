package ru.larineldar.filmsearch.data

import ru.larineldar.filmsearch.data.entity.Film

interface MainRepository {
    fun putAllFilmsToDb(films: List<Film>)
    fun getAllFilmsFromDB(): List<Film>
    fun clearDB()
}