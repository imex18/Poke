package com.example.poke.ui.details


import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.poke.R
import com.example.poke.ui.MainActivity
import com.example.poke2.poke2.ui.list.PokemonViewModel
import kotlinx.android.synthetic.main.fragment_details.*
import kotlinx.android.synthetic.main.item_view.*
import kotlinx.android.synthetic.main.item_view.view.*

class DetailsFragment : Fragment(R.layout.fragment_details) {

    private val args by navArgs<DetailsFragmentArgs>()
    lateinit var viewModel: PokemonViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        viewModel = (activity as MainActivity).viewModel

        Log.e("URL", args.pokemonDetails.spriteUrl)

        populateFields()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.findItem(R.id.app_bar_search).isVisible = false
        super.onCreateOptionsMenu(menu, inflater)
    }


    fun populateFields(){

        Glide.with(this)
                .load(args.pokemonDetails.spriteUrl)
                .into(iv_details_pokemon)

        val name = resources.getString(R.string.Name, args.pokemonDetails.name)
        tv_details_name.setText(name)

        val weight = resources.getString(R.string.Weight, args.pokemonDetails.weight)
        tv_details_weight.setText(weight)

        val height = resources.getString(R.string.Height, args.pokemonDetails.height)
        tv_details_height.setText(height)

    }
}