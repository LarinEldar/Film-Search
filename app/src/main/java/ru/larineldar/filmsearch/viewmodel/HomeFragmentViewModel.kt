package ru.larineldar.filmsearch.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.larineldar.filmsearch.App
import ru.larineldar.filmsearch.domain.Film
import ru.larineldar.filmsearch.domain.Interactor

class HomeFragmentViewModel : ViewModel(){
    val filmsListLiveData = MutableLiveData<List<Film>>()
    private var interactor: Interactor = App.instance.interactor

    init {
        val films = interactor.getFilmsMainRepository()
        filmsListLiveData.postValue(films)
    }
}