package com.example.poke.data.local


import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.poke.model.Pokemon

@Dao
interface PokemonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun savePokemon (item:Pokemon)

    @Query("SELECT * FROM pokemons")
    fun getSavedPokemons(): LiveData<List<Pokemon>>

    @Delete
    suspend fun deletePokemon(item:Pokemon)


}