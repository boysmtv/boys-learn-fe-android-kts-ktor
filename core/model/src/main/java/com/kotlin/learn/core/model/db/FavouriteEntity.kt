package com.kotlin.learn.core.model.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavouriteEntity(
    val userId: Int,
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val title: String,
    val body: String
)