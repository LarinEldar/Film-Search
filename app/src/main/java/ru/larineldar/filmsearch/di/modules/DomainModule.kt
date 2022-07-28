package ru.larineldar.filmsearch.di.modules

import dagger.Binds
import dagger.Module
import ru.larineldar.filmsearch.domain.Interactor
import ru.larineldar.filmsearch.domain.InteractorImpl
import javax.inject.Singleton

@Module
interface DomainModule {
    @Binds
    @Singleton
    fun bindInteractor(interactor: InteractorImpl): Interactor
}