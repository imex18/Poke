package com.example.poke.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
@Entity (tableName = "pokemons")
class Pokemon(
    val name: String,
    val spriteUrl: String,
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val weight: Int,
    val height: Int,
) :Parcelable {
}