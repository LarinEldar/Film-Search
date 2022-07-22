package ru.larineldar.filmsearch

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import ru.larineldar.filmsearch.di.DI


class App : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        startKoin{
            androidContext(this@App)
            androidLogger()
            modules(listOf(DI.mainModule))
        }
    }

    companion object {
        lateinit var instance: App
            private set
    }
}