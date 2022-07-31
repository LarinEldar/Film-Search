package ru.larineldar.filmsearch.di.modules

import dagger.Binds
import dagger.Module
import dagger.Provides
import ru.larineldar.filmsearch.data.MainRepository
import ru.larineldar.filmsearch.data.MainRepositoryImpl
import javax.inject.Singleton

@Module
interface DatabaseModule {
    @Binds
    @Singleton
    fun bindRepository(repo: MainRepositoryImpl): MainRepository

}