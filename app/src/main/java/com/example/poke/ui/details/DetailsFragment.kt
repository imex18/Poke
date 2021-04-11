package com.example.poke.ui.details


import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.poke.R
import com.example.poke.model.Pokemon
import com.example.poke.ui.MainActivity
import com.example.poke2.poke2.ui.list.PokemonViewModel
import kotlinx.android.synthetic.main.fragment_details.*
import kotlinx.android.synthetic.main.item_view.*
import kotlinx.android.synthetic.main.item_view.view.*

class DetailsFragment : Fragment(R.layout.fragment_details) {

    private val args by navArgs<DetailsFragmentArgs>()
    lateinit var viewModel: PokemonViewModel
    lateinit var currentItem :Pokemon

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        viewModel = (activity as MainActivity).viewModel

        currentItem = args.pokemonDetails

        Log.e("URL", currentItem.spriteUrl)

        populateFields()

        fab.setOnClickListener {

            viewModel.savePokemon(currentItem)

            Toast.makeText(context, "${currentItem.name} added to Favourites", Toast.LENGTH_SHORT).show()

        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.findItem(R.id.app_bar_search).isVisible = false
        super.onCreateOptionsMenu(menu, inflater)
    }


    fun populateFields(){

        Glide.with(this)
                .load(currentItem.spriteUrl)
                .into(iv_details_pokemon)

        val name = resources.getString(R.string.Name, currentItem.name)
        tv_details_name.setText(name)

        val weight = resources.getString(R.string.Weight, currentItem.weight)
        tv_details_weight.setText(weight)

        val height = resources.getString(R.string.Height, currentItem.height)
        tv_details_height.setText(height)

    }
}