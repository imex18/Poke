package com.example.poke.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PokemonURL(
    val name: String,
    val url: String
):Parcelable