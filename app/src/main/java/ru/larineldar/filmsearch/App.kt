package ru.larineldar.filmsearch

import android.app.Application
import ru.larineldar.filmsearch.di.AppComponent
import ru.larineldar.filmsearch.di.DaggerAppComponent

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
    }
}