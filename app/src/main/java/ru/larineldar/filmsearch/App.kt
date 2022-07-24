package ru.larineldar.filmsearch

import android.app.Application
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.larineldar.filmsearch.data.ApiConstants
import ru.larineldar.filmsearch.data.TmdbApi
import ru.larineldar.filmsearch.di.AppComponent
import ru.larineldar.filmsearch.di.DaggerAppComponent
import ru.larineldar.filmsearch.domain.Interactor
import java.util.concurrent.TimeUnit

class App : Application() {
    lateinit var dagger: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        dagger = DaggerAppComponent.create()
    }

    companion object {
        lateinit var instance: App
            private set
        const val timeout = 30L
    }
}