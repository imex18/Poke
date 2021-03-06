package com.example.poke.data.remote

import com.example.poke.model.PokemonDetailsReponse
import com.example.poke.model.PokemonListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface PokeAPI {

    @GET("pokemon/")
    suspend fun getPokemons(): Response<PokemonListResponse>

    @GET
    suspend fun getPokemonDetails(@Url url: String): Response<PokemonDetailsReponse>

}