package ru.larineldar.filmsearch.domain

import android.content.SharedPreferences
import ru.larineldar.filmsearch.data.entity.Film
import ru.larineldar.filmsearch.viewmodel.HomeFragmentViewModel

interface Interactor {
    fun getFilmsFromApi(page: Int, callback: HomeFragmentViewModel.ApiCallback)

    fun getCategoryFromPreference(): String

    fun setCategoryToPreference(category: String)

    fun registerOnSharedPreferenceChangeListener(listener: SharedPreferences.OnSharedPreferenceChangeListener)

    fun getFilmsFromDB(): List<Film>

    fun clearDB()
}