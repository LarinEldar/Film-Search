package ru.larineldar.filmsearch.viewmodel

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.larineldar.filmsearch.App
import ru.larineldar.filmsearch.domain.Interactor
import javax.inject.Inject

class SettingsFragmentViewModel: ViewModel() {
    @Inject
    lateinit var interactor: Interactor
    val categoryLiveData: MutableLiveData<String> = MutableLiveData()

    init {
        App.instance.dagger.inject(this)
        getCategory()
    }

    private fun getCategory(){
        categoryLiveData.postValue(interactor.getCategoryFromPreference())
    }

    fun putCategory(category: String){
        interactor.setCategoryToPreference(category)
        getCategory()
    }
}