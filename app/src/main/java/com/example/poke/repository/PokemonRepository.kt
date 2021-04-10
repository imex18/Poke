package com.example.poke.repository

import android.util.Log
import com.example.poke.data.remote.RetrofitInstance
import com.example.poke.model.PokemonURL

class PokemonRepository {

    suspend fun getPokemons(): List<PokemonURL>? {

        val pokemonsResponse = RetrofitInstance.api.getPokemons()

        if (pokemonsResponse.isSuccessful) {

            val results = pokemonsResponse.body()?.results.orEmpty()
            Log.d("Response", results.toString())
            return results

        } else {
            Log.d("Response", pokemonsResponse.errorBody().toString())
            return null
        }
    }


}