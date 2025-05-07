package com.example.data.local.watched_eps_db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface TitleWatchedEpsDao {

    //Use like UPDATE watched episodes list
    @Upsert
    suspend fun upsertTitle(title: TitleWatchedEps)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTitle(title: TitleWatchedEps)

    @Query("SELECT * FROM titlewatchedeps WHERE titleId = :titleId")
    fun getWatched(titleId: Int): Flow<List<TitleWatchedEps>>
}