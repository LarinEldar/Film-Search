package ru.larineldar.filmsearch.di

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.larineldar.filmsearch.BuildConfig
import ru.larineldar.filmsearch.data.ApiConstants
import ru.larineldar.filmsearch.data.TmdbApi
import ru.larineldar.filmsearch.domain.Interactor
import ru.larineldar.filmsearch.domain.Remote
import java.util.concurrent.TimeUnit

object DI {
    val mainModule = module {
        single<TmdbApi> { Remote.retrofitService }
        single { Interactor(get()) }
    }
}