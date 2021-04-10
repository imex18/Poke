package com.example.poke2.poke2.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
class Pokemon(
    val name: String,
    val spriteUrl: String,
    val id:Int,
    val weight: Int,
    val height: Int,
) :Parcelable {
}