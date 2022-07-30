package ru.larineldar.filmsearch.data

import android.content.ContentValues
import android.database.Cursor
import ru.larineldar.filmsearch.data.db.DatabaseHelper
import ru.larineldar.filmsearch.domain.Film
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepositoryImpl @Inject constructor(dbHelper: DatabaseHelper): MainRepository{
    private val sqlDb = dbHelper.readableDatabase
    private lateinit var cursor: Cursor

    override fun putFilmToDb(film: Film){
        val cv = ContentValues().apply {
            put(DatabaseHelper.COLUMN_TITLE, film.title)
            put(DatabaseHelper.COLUMN_POSTER, film.poster)
            put(DatabaseHelper.COLUMN_DESCRIPTION, film.description)
            put(DatabaseHelper.COLUMN_RATING, film.rating)
        }

        sqlDb.insert(DatabaseHelper.TABLE_NAME, null, cv)
    }

    override fun getAllFilmsFromDB(): List<Film>{
        cursor = sqlDb.rawQuery("SELECT * FROM ${DatabaseHelper.TABLE_NAME}", null)
        val result = mutableListOf<Film>()

        if (cursor.moveToFirst()){
            do {
                result.add(Film(
                    title = cursor.getString(DatabaseHelper.INDEX_TITLE),
                    poster = cursor.getString(DatabaseHelper.INDEX_POSTER),
                    description = cursor.getString(DatabaseHelper.INDEX_DESCRIPTION),
                    rating = cursor.getDouble(DatabaseHelper.INDEX_RATING)
                ))
            } while (cursor.moveToNext())
        }

        cursor.close()
        return result
    }

    override fun clearDB() {
        getAllFilmsFromDB().forEach {
            sqlDb.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper.COLUMN_TITLE + "= ?", arrayOf(it.title))
        }
    }


}