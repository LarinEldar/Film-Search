package ru.larineldar.filmsearch.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.larineldar.filmsearch.data.entity.Film

@Dao
interface FilmDao {
    @Query("SELECT * FROM ${Film.TABLE_NAME}")
    fun getCashedFilms(): List<Film>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAllFilms(films: List<Film>)

    @Delete
    fun deleteAll(films: List<Film>)
}