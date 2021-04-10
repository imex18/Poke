package com.example.poke.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

import androidx.navigation.findNavController
import com.example.poke.R
import com.example.poke.ui.list.ListFragmentDirections
import com.example.poke2.poke2.model.Pokemon
import kotlinx.android.synthetic.main.item_view.view.*

class PokemonAdapter (
    var list : List<Pokemon>?
        ) : RecyclerView.Adapter<PokemonAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
         val view =
             LayoutInflater.from(parent.context)
                 .inflate(R.layout.item_view, parent, false)
         return ItemViewHolder(view)
     }

     override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
         val currentitem = list?.get(position)
         holder.itemView.apply {

             Glide.with(context)
                 .load(currentitem?.spriteUrl.orEmpty())
                 .placeholder(R.drawable.background_pokemon_item)
                 .error(R.drawable.background_pokemon_item)
                 .into(iv_pokemon)

             tv_pokemon_name.setText(currentitem?.name)


                row_layout.setOnClickListener {

                    val action = ListFragmentDirections.actionListFragmentToDetailsFragment(currentitem!!)
                    findNavController().navigate(action)

         }
 }}

    override fun getItemCount(): Int {
        return list!!.size }

        }

