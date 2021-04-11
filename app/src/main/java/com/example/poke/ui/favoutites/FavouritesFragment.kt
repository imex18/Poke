package com.example.poke.ui.favoutites

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.poke.R
import com.example.poke.adapter.FavouritesAdapter
import com.example.poke.adapter.PokemonAdapter
import com.example.poke.model.Pokemon
import com.example.poke.ui.MainActivity
import com.example.poke2.poke2.ui.list.PokemonViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_favourites.*


class FavouritesFragment : Fragment(R.layout.fragment_favourites) {


    private lateinit var viewModel: PokemonViewModel
    private lateinit var adapter: FavouritesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        viewModel = (activity as MainActivity).viewModel


        adapter = FavouritesAdapter(listOf())
        rv_favourites.adapter = adapter
        rv_favourites.layoutManager = LinearLayoutManager(requireContext())




        viewModel.getSavedPokemons().observe(viewLifecycleOwner, Observer { items ->

            adapter.list = items
            adapter.notifyDataSetChanged()
        })


             val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                val position = viewHolder.adapterPosition
                val item = adapter.list?.get(position)
                viewModel.deletePokemon(item!!)

                Snackbar.make(requireView(), "Successfully deleted ", Snackbar.LENGTH_SHORT)
                    .apply {
                        setAction("Undo") {
                            viewModel.savePokemon(item)
                        }.show()
                    }

            }
        }

        ItemTouchHelper(itemTouchHelperCallback).apply {

            attachToRecyclerView(rv_favourites)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.findItem(R.id.app_bar_search).isVisible = false
        menu.findItem(R.id.options_menu_favoutites).isVisible = false
        super.onCreateOptionsMenu(menu, inflater)
    }


    }










