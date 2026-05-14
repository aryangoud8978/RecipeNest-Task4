package com.example.recipenest.api

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.recipenest.model.FavoriteRecipeEntity

@Database(
    entities = [FavoriteRecipeEntity::class],
    version = 1
)
abstract class RecipeDatabase : RoomDatabase() {

    abstract fun favoriteRecipeDao(): FavoriteRecipeDao

    companion object {

        @Volatile
        private var INSTANCE: RecipeDatabase? = null

        fun getDatabase(
            context: Context
        ): RecipeDatabase {

            return INSTANCE ?: synchronized(this) {

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RecipeDatabase::class.java,
                    "recipe_database"
                ).build()

                INSTANCE = instance

                instance
            }
        }
    }
}