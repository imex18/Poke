package com.example.poke.model

import com.google.gson.annotations.SerializedName

class PokemonDetailsReponse(
    val id: Int,
    val name: String,
    val sprites: Sprites,
    val weight: Int,
    val height: Int,
    val baseExperience: BaseExperience?
) {

    data class Sprites(@SerializedName("front_default") val frontDefault: String)
    data class BaseExperience(@SerializedName("base_experience") val baseExperience: Int)


}