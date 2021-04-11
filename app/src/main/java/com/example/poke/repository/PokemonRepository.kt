package com.example.poke.repository

import android.util.Log
import com.example.poke.data.local.PokemonDataBase
import com.example.poke.data.remote.RetrofitInstance
import com.example.poke.model.Pokemon
import com.example.poke.model.PokemonURL
import com.example.poke.util.Constants


class PokemonRepository (val db:PokemonDataBase) {

    suspend fun getPokemons(): List<Pokemon>? {

        val pokemonsResponse = RetrofitInstance.api.getPokemons()

        if (pokemonsResponse.isSuccessful) {
            val results = pokemonsResponse.body()?.results.orEmpty()
            Log.d("Response", results.toString())
            return loadPokemonsDetails(results)
        } else {

            Log.d("Response", pokemonsResponse.errorBody().toString())
            return null
        }
    }

    private suspend fun loadPokemonsDetails(pokemons: List<PokemonURL>): List<Pokemon> {
        val mappedPokemons = pokemons.map { onePokemon ->
            // extract for each pokemon it's details by using it's url
            // based on pokemon details, create a new object (Pokemon) where you will have everything
            val shortenUrl = onePokemon.url.removePrefix(Constants.BASE_URL)
            val response = RetrofitInstance.api.getPokemonDetails(shortenUrl)

            if (response.isSuccessful) {
                val pokemonDetailsResponse = response.body()
                Log.d("Response", pokemonDetailsResponse.toString())

                if (pokemonDetailsResponse != null) {
                    Pokemon(
                        pokemonDetailsResponse.id,
                        onePokemon.name,
                        pokemonDetailsResponse.sprites.frontDefault,
                        pokemonDetailsResponse.weight,
                        pokemonDetailsResponse.height
                    )

                } else {

                    Pokemon(0,onePokemon.name, "", 0, 0 )

                }
            } else {

                Log.d("Response", response.errorBody().toString())
                Pokemon(0,onePokemon.name, "", 0, 0 )
            }
        }
        return mappedPokemons
    }


    // local database operations

  suspend fun savePokemon(item:Pokemon) = db.getDao().savePokemon(item)
  fun getSavedPokemons ()= db.getDao().getSavedPokemons()
  suspend fun deletePokemon(item: Pokemon) = db.getDao().deletePokemon(item)


}