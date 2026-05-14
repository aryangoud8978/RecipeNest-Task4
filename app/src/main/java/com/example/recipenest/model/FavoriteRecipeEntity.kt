package com.example.recipenest.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_recipes")
data class FavoriteRecipeEntity(

    @PrimaryKey
    val id: String,

    val name: String,

    val imageUrl: String,

    val category: String,

    val instructions: String
)