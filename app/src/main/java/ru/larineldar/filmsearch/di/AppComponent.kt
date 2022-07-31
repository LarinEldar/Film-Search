package ru.larineldar.filmsearch.di

import dagger.BindsInstance
import dagger.Component
import ru.larineldar.filmsearch.App
import ru.larineldar.filmsearch.di.modules.DatabaseModule
import ru.larineldar.filmsearch.di.modules.DomainModule
import ru.larineldar.filmsearch.di.modules.RemoteModule
import ru.larineldar.filmsearch.viewmodel.HomeFragmentViewModel
import ru.larineldar.filmsearch.viewmodel.SettingsFragmentViewModel
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

    fun inject(settingsFragmentViewModel: SettingsFragmentViewModel)

    @Component.Builder
    interface Builder{
        @BindsInstance
        fun application(app: App): Builder
        fun build(): AppComponent
    }
}