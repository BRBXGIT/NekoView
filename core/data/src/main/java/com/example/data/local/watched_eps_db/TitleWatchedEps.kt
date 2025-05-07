package com.example.data.local.watched_eps_db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TitleWatchedEps(
    @PrimaryKey
    val titleId: Int,
    val watchedEps: List<Int> = emptyList()
)
