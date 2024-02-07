package com.example.cocktails.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cocktails.R
import com.example.cocktails.databinding.CocktailItemBinding
import com.example.cocktails.domain.Cocktail

class CocktailAdapter(
    private val onClickListener: (Cocktail) -> Unit
): ListAdapter<Cocktail, CocktailAdapter.ViewHolder>(DiffUtil()) {

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cocktail_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding = CocktailItemBinding.bind(holder.itemView)
        val item = currentList[position]

        binding.name.text = item.name
        Glide.with(holder.itemView.context)
            .load(item.src)
            .into(binding.icon)

        binding.root.setOnClickListener { onClickListener.invoke(item) }
    }


}

class DiffUtil(): DiffUtil.ItemCallback<Cocktail>() {
    override fun areItemsTheSame(oldItem: Cocktail, newItem: Cocktail): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Cocktail, newItem: Cocktail): Boolean = oldItem == newItem

}
