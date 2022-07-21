package ru.larineldar.filmsearch

import android.app.Application
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import ru.larineldar.filmsearch.data.ApiConstants
import ru.larineldar.filmsearch.data.MainRepository
import ru.larineldar.filmsearch.data.TmdbApi
import ru.larineldar.filmsearch.domain.Interactor
import java.util.concurrent.TimeUnit

class App : Application() {
    lateinit var interactor: Interactor

    override fun onCreate() {
        super.onCreate()

        val okHttpClient = OkHttpClient.Builder()
            .callTimeout(timeout, TimeUnit.SECONDS)
            .readTimeout(timeout, TimeUnit.SECONDS)
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

        val retrofitService = retrofit.create(TmdbApi::class.java)

        instance = this
        interactor = Interactor(retrofitService)
    }

    companion object {
        lateinit var instance: App
            private set
        const val timeout = 30L
    }
}