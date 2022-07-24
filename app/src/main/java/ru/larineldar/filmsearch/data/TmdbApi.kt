package ru.larineldar.filmsearch.data

import ru.larineldar.filmsearch.data.entity.TmdbResultsDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TmdbApi {
    @GET("movie/popular")
    fun getPopFilms(
        @Query("api_key") apikey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Call<TmdbResultsDto>
}