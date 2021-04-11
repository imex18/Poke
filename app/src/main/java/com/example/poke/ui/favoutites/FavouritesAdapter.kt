package com.example.poke.ui.favoutites

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.poke.R
import com.example.poke.model.Pokemon
import kotlinx.android.synthetic.main.item_view_favourites.view.*


class FavouritesAdapter(
    var list: List<Pokemon>?
) : RecyclerView.Adapter<FavouritesAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_view_favourites, parent, false)
        return ItemViewHolder(view)
    }


    override fun getItemCount(): Int {
        return list!!.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currentitem = list?.get(position)
        holder.itemView.apply {

            Glide.with(context)
                .load(currentitem?.spriteUrl.orEmpty())
                .into(favoutites_image)

            favourites_name.setText(currentitem?.name)

            // displaying current item on the details screen.
            item_view_favourites.setOnClickListener {

                val action = FavouritesFragmentDirections.actionFavouritesFragmentToDetailsFragment(
                    currentitem!!
                )
                findNavController().navigate(action)

            }

        }
    }


}


