package com.example.poke.data.remote

import com.example.poke.model.PokemonListResponse
import retrofit2.Response
import retrofit2.http.GET

interface PokeAPI {

    @GET("pokemon/")
    suspend fun getPokemons(): Response<PokemonListResponse>

}