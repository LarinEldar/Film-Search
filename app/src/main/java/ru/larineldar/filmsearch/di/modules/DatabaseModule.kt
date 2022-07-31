package ru.larineldar.filmsearch.di.modules

import android.content.Context
import androidx.room.Room
import dagger.Binds
import dagger.Module
import dagger.Provides
import ru.larineldar.filmsearch.data.MainRepository
import ru.larineldar.filmsearch.data.MainRepositoryImpl
import ru.larineldar.filmsearch.data.dao.FilmDao
import ru.larineldar.filmsearch.data.db.AppDatabase
import ru.larineldar.filmsearch.di.AppContextQualifier
import javax.inject.Singleton

@Module
interface DatabaseModule {
    @Binds
    @Singleton
    fun bindRepository(repo: MainRepositoryImpl): MainRepository

    companion object {
        @Provides
        @Singleton
        fun provideDao(db: AppDatabase): FilmDao {
            return db.getFilmDao()
        }

        @Provides
        @Singleton
        fun provideDB(@AppContextQualifier context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, AppDatabase.DB_NAME)
                .build()
        }
    }
}