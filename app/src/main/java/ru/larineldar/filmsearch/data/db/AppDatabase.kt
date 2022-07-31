package ru.larineldar.filmsearch.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.larineldar.filmsearch.data.dao.FilmDao
import ru.larineldar.filmsearch.data.entity.Film

@Database(entities = [Film::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase(){
    abstract fun getFilmDao(): FilmDao

    companion object{
        const val DB_NAME = "films_database"
    }
}