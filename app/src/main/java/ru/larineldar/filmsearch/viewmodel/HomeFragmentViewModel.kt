package ru.larineldar.filmsearch.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ru.larineldar.filmsearch.domain.Film
import ru.larineldar.filmsearch.domain.Interactor

class HomeFragmentViewModel : ViewModel(), KoinComponent{
    val filmsListLiveData = MutableLiveData<List<Film>>()
    private val interactor: Interactor by inject()
    private var page = 1

    init {
        loadNextPage()
    }

    fun loadNextPage(){
        interactor.getFilmsFromApi(page, object : ApiCallback {
            override fun onSuccess(films: List<Film>) {
                val oldItems = filmsListLiveData.value
                val newItems = mutableListOf<Film>()

                if(oldItems != null)
                    newItems.addAll(oldItems)
                newItems.addAll(films)

                filmsListLiveData.postValue(newItems)
            }

            override fun onFailure() {}
        })
        page++
    }

    interface ApiCallback {
        fun onSuccess(films: List<Film>)
        fun onFailure()
    }
}