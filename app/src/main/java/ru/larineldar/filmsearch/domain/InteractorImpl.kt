package ru.larineldar.filmsearch.domain

import android.content.SharedPreferences
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.larineldar.filmsearch.data.API
import ru.larineldar.filmsearch.data.MainRepository
import ru.larineldar.filmsearch.data.PreferenceProvider
import ru.larineldar.filmsearch.data.TmdbApi
import ru.larineldar.filmsearch.data.entity.Film
import ru.larineldar.filmsearch.data.entity.TmdbResultsDto
import ru.larineldar.filmsearch.utils.Converter
import ru.larineldar.filmsearch.viewmodel.HomeFragmentViewModel
import java.util.Locale
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InteractorImpl @Inject constructor(
    private val retrofitService: TmdbApi,
    private val repo: MainRepository,
    private val preference: PreferenceProvider
) : Interactor {

    override fun getFilmsFromApi(page: Int, callback: HomeFragmentViewModel.ApiCallback) {
        retrofitService.getFilms(
            getCategoryFromPreference(),
            API.KEY,
            Locale.getDefault().toString(),
            page
        )
            .enqueue(object : Callback<TmdbResultsDto> {
                override fun onResponse(
                    call: Call<TmdbResultsDto>,
                    response: Response<TmdbResultsDto>
                ) {
                    val films = Converter.converterApiListToDtoList(response.body()?.results)
                    repo.putAllFilmsToDb(films)
                    callback.onSuccess(films)
                }

                override fun onFailure(call: Call<TmdbResultsDto>, t: Throwable) {
                    callback.onFailure()
                }
            })
    }

    override fun getCategoryFromPreference(): String {
        return preference.getCategory()
    }

    override fun setCategoryToPreference(category: String) {
        preference.setCategory(category)
    }

    override fun registerOnSharedPreferenceChangeListener(listener: SharedPreferences.OnSharedPreferenceChangeListener) {
        preference.registerOnSharedPreferenceChangeListener(listener)
    }

    override fun getFilmsFromDB(): List<Film> = repo.getAllFilmsFromDB()

    override fun clearDB(){
        repo.clearDB()
    }
}