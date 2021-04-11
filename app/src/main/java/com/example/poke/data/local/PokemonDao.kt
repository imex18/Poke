package com.example.poke.data.local


import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.poke.model.Pokemon

@Dao
interface PokemonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun savePokemon (item:Pokemon) : Long

    @Delete
    suspend fun deletePokemon(item:Pokemon)
}