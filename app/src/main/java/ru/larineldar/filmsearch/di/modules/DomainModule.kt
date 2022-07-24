package ru.larineldar.filmsearch.di.modules

import dagger.Module
import dagger.Provides
import ru.larineldar.filmsearch.data.TmdbApi
import ru.larineldar.filmsearch.domain.Interactor
import javax.inject.Singleton

@Module
class DomainModule {
    @Provides
    @Singleton
    fun provideInteractor(tmdbApi: TmdbApi): Interactor = Interactor(tmdbApi)
}