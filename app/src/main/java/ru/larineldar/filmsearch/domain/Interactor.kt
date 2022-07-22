package ru.larineldar.filmsearch.domain

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.larineldar.filmsearch.data.API
import ru.larineldar.filmsearch.data.MainRepository
import ru.larineldar.filmsearch.data.TmdbApi
import ru.larineldar.filmsearch.data.entity.TmdbResultsDto
import ru.larineldar.filmsearch.utils.Converter
import ru.larineldar.filmsearch.viewmodel.HomeFragmentViewModel
import java.util.Locale

class Interactor (private val retrofitService: TmdbApi){
    fun getFilmsFromApi(page: Int, callback: HomeFragmentViewModel.ApiCallback) {
        retrofitService.getPopFilms(API.KEY, Locale.getDefault().toLanguageTag(), page)
            .enqueue(object : Callback<TmdbResultsDto>{
                override fun onResponse(call: Call<TmdbResultsDto>, response: Response<TmdbResultsDto>) {
                    callback.onSuccess(Converter.converterApiListToDtoList(response.body()?.results))
                }

                override fun onFailure(call: Call<TmdbResultsDto>, t: Throwable) {
                    callback.onFailure()
                }
            })
    }
}