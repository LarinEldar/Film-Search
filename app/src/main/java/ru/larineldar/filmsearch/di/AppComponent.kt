package ru.larineldar.filmsearch.di

import dagger.Component
import ru.larineldar.filmsearch.di.modules.DatabaseModule
import ru.larineldar.filmsearch.di.modules.DomainModule
import ru.larineldar.filmsearch.di.modules.RemoteModule
import ru.larineldar.filmsearch.viewmodel.HomeFragmentViewModel
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        DatabaseModule::class,
        DomainModule::class,
        RemoteModule::class
    ]
)
interface AppComponent {
    fun inject(homeFragmentViewModel: HomeFragmentViewModel)
}