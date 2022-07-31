package ru.larineldar.filmsearch.data.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = Film.TABLE_NAME, indices = [Index(value = [Film.COLUMN_TITLE], unique = true)])
data class Film (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = COLUMN_TITLE) val title: String,
    @ColumnInfo(name = COLUMN_POSTER) val poster: String,
    @ColumnInfo(name = COLUMN_DESCRIPTION) val description: String,
    @ColumnInfo(name = COLUMN_RATING) var rating : Double = 0.0,
    var isInFavorites: Boolean = false,

): Parcelable {
    companion object{
        const val TABLE_NAME = "cashed_films"
        const val COLUMN_TITLE = "title"
        const val COLUMN_POSTER = "poster"
        const val COLUMN_DESCRIPTION = "description"
        const val COLUMN_RATING = "rating"
    }
}