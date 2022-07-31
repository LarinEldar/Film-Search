package ru.larineldar.filmsearch.di.modules

import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import ru.larineldar.filmsearch.App
import ru.larineldar.filmsearch.data.PreferenceProvider
import ru.larineldar.filmsearch.di.AppContextQualifier
import ru.larineldar.filmsearch.domain.Interactor
import ru.larineldar.filmsearch.domain.InteractorImpl
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
interface DomainModule {
    @Binds
    @Singleton
    fun bindInteractor(interactor: InteractorImpl): Interactor

    @Binds
    @AppContextQualifier
    fun bindAppContext(app: App): Context
}