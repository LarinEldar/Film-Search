package ru.larineldar.filmsearch.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.larineldar.filmsearch.App
import ru.larineldar.filmsearch.domain.Film
import ru.larineldar.filmsearch.domain.Interactor
import javax.inject.Inject

class HomeFragmentViewModel : ViewModel(){
    val filmsListLiveData = MutableLiveData<List<Film>>()
    @Inject
    lateinit var interactor: Interactor
    private var page = 1

    init {
        App.instance.dagger.inject(this)
        instance = this
        loadNextPage()
        interactor.registerOnSharedPreferenceChangeListener{_, _ ->
            instance.reloadPage()
        }
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

            override fun onFailure() {
                filmsListLiveData.postValue(interactor.getFilmsFromDB())
            }
        })
        page++
    }

    fun reloadPage(){
        page = 1
        filmsListLiveData.postValue(listOf())
        interactor.clearDB()
        loadNextPage()
    }

    interface ApiCallback {
        fun onSuccess(films: List<Film>)
        fun onFailure()
    }

    companion object{
        private lateinit var instance: HomeFragmentViewModel
    }
}