package com.example.data.local.watched_eps_db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [TitleWatchedEps::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class TitleWatchedEpsDb: RoomDatabase() {

    abstract fun titleWatchedEpsDao(): TitleWatchedEpsDao
}