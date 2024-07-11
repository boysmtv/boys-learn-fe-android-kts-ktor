package com.kotlin.learn.core.model.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity(
    val postId: Int,
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val email: String,
    val body: String
)