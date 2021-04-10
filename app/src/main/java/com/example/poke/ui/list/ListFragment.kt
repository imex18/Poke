package com.example.poke.ui.list


import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.poke.R
import com.example.poke.adapter.PokemonAdapter
import com.example.poke.ui.MainActivity
import com.example.poke2.poke2.ui.list.PokemonViewModel
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : Fragment(R.layout.fragment_list) {

    lateinit var viewModel: PokemonViewModel
    private lateinit var pokemonAdapter: PokemonAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        viewModel = (activity as MainActivity).viewModel
        viewModel.getPokemons()



        viewModel.myResponse.observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                is PokemonViewModel.Resource.Success -> {
                    //initialize the adapter and pass it to the recyclerview
                    pokemonAdapter = PokemonAdapter(state.data)
                    rv_list.apply {
                        adapter = pokemonAdapter
                        layoutManager = GridLayoutManager(context, 2)
                    }
                }

                is PokemonViewModel.Resource.Error -> {
                    // show a retry dialog
                    retryDialog()
                }

                is PokemonViewModel.Resource.Loading -> {
                    // show a progress bar
                    progressBar.isVisible
                }
            }
        })
    }

    private fun retryDialog() {
        context?.let {
            AlertDialog.Builder(it)
                .setMessage(viewModel.myResponse.toString())
                .setPositiveButton("Try again") { _, _ -> viewModel.getPokemons() }
                .setNegativeButton("Dismiss") { dialoginterface, _ -> dialoginterface.dismiss() }
                .create()
                .show()
        }
    }

}