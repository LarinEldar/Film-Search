package ru.larineldar.filmsearch.domain

import ru.larineldar.filmsearch.data.MainRepository

class Interactor (private val repo: MainRepository){
    fun getFilmsMainRepository() = repo.films
}