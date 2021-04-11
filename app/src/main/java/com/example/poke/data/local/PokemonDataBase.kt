package com.example.poke.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

import com.example.poke.model.Pokemon

@Database(entities = [Pokemon::class],
    version = 1)

abstract class PokemonDataBase : RoomDatabase() {

    abstract fun getDao() : PokemonDao

    companion object {

        @Volatile
        private var instance: PokemonDataBase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance
            ?: synchronized(LOCK) {
                instance
                    ?: createDatabase(
                        context
                    ).also { instance = it }
            }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                PokemonDataBase::class.java, "pokemon_db.db").build()
    }
}