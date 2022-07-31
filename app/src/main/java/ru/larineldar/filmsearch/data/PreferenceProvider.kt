package ru.larineldar.filmsearch.data

import android.content.Context
import android.content.SharedPreferences
import ru.larineldar.filmsearch.di.AppContextQualifier
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreferenceProvider @Inject constructor(@AppContextQualifier val context: Context) {
    private val preference = context.getSharedPreferences(SETTINGS_FILE, Context.MODE_PRIVATE)

    fun setCategory(category: String){
        preference.edit().putString(KEY_CATEGORY, category).apply()
    }

    fun getCategory(): String {
        return preference.getString(KEY_CATEGORY, DEFAULT_CATEGORY) ?: DEFAULT_CATEGORY
    }

    companion object {
        const val SETTINGS_FILE = "settings"
        const val KEY_CATEGORY = "category"
        const val DEFAULT_CATEGORY = "popular"
    }

    fun registerOnSharedPreferenceChangeListener(listener: SharedPreferences.OnSharedPreferenceChangeListener){
        preference.registerOnSharedPreferenceChangeListener(listener)
    }
}