package ru.larineldar.filmsearch.data

import ru.larineldar.filmsearch.data.dao.FilmDao
import ru.larineldar.filmsearch.data.entity.Film
import java.util.concurrent.Executors
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepositoryImpl @Inject constructor(private val filmDao: FilmDao): MainRepository{

    override fun putAllFilmsToDb(films: List<Film>){
        Executors.newSingleThreadExecutor().execute{
            filmDao.insertAllFilms(films)
        }
    }

    override fun getAllFilmsFromDB(): List<Film>{
        var list = listOf<Film>()

        object: Thread(){
            override fun run(){
                list = filmDao.getCashedFilms()
            }
        }.apply {
            start()
            join()
        }
        return list
    }

    override fun clearDB() {
        Executors.newSingleThreadExecutor().execute {
            filmDao.deleteAll(getAllFilmsFromDB())
        }
    }


}