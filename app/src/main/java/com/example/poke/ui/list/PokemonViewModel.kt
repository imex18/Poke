package com.example.poke2.poke2.ui.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.poke.model.PokemonURL
import com.example.poke.repository.PokemonRepository
import com.example.poke2.poke2.model.Pokemon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PokemonViewModel(private val repository: PokemonRepository) : ViewModel() {

    private var allPokemonURLS: List<Pokemon> = emptyList()
    var myResponse: MutableLiveData<Resource<List<Pokemon>>> = MutableLiveData()



    fun getPokemons() = viewModelScope.launch {

        allPokemonURLS = emptyList()
        myResponse.value = Resource.Loading()

        val pokemons = withContext(Dispatchers.IO) { repository.getPokemons() }

        if (pokemons != null) {
            allPokemonURLS = pokemons
            myResponse.value = Resource.Success(pokemons)
        }
        else {
            // We have to notify about the error
            myResponse.value = Resource.Error("OOPS... Something went wrong")
        }
    }



    fun filterPokemons(searchText: String) {
        // find all which have the searchText in their names
        val filteredPokemons = allPokemonURLS.filter { it.name.contains(searchText) }
        // update the myResponse with filtered pokomons
        myResponse.value = Resource.Success(filteredPokemons)
    }




    sealed class Resource<T>(
        val data: T? = null,
        val message: String? = null
    ) {

        class Success<T>(data: T) : Resource<T>(data)
        class Error<T>(message: String?, data: T? = null) : Resource<T>(data, message)
        class Loading<T> : Resource<T>()

    }




}