package ru.larineldar.filmsearch.domain

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.larineldar.filmsearch.BuildConfig
import ru.larineldar.filmsearch.data.ApiConstants
import ru.larineldar.filmsearch.data.TmdbApi
import java.util.concurrent.TimeUnit

object Remote{
    val retrofitService: TmdbApi
    private const val TIMEOUT = 30L

    init{
        val okHttpClient = OkHttpClient.Builder()
            .callTimeout(TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().apply {
                if(BuildConfig.DEBUG){
                    level = HttpLoggingInterceptor.Level.BASIC
                }
            })
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        retrofitService = retrofit.create(TmdbApi::class.java)
    }

}