package ru.larineldar.filmsearch

import android.app.Application
import ru.larineldar.filmsearch.data.MainRepository
import ru.larineldar.filmsearch.domain.Interactor

class App : Application() {
    lateinit var repo: MainRepository
    lateinit var interactor: Interactor

    override fun onCreate() {
        super.onCreate()
        instance = this
        repo = MainRepository()
        interactor = Interactor(repo)
    }

    companion object {
        lateinit var instance: App
            private set
    }
}