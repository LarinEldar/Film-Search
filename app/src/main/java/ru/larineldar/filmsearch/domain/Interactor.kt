package ru.larineldar.filmsearch.domain

import ru.larineldar.filmsearch.viewmodel.HomeFragmentViewModel

interface Interactor {
    fun getFilmsFromApi(page: Int, callback: HomeFragmentViewModel.ApiCallback)
}