package de.syntaxinstitut.android_ww_template.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import de.syntaxinstitut.android_ww_template.R
import de.syntaxinstitut.android_ww_template.data.datamodels.Character
import de.syntaxinstitut.android_ww_template.databinding.ListItemBinding
import de.syntaxinstitut.android_ww_template.databinding.ListItemFavoritBinding
import de.syntaxinstitut.android_ww_template.ui.FavoritFragmentDirections
import de.syntaxinstitut.android_ww_template.ui.MainViewModel

class SecondAdapter(
    private var dataset: List<Character>,
    private var viewModel: MainViewModel,
) : RecyclerView.Adapter<SecondAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(val binding: ListItemFavoritBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ListItemFavoritBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        var item = dataset[position]

        holder.binding.potterIV.load(item.image)
        holder.binding.name2TV.text = item.name
        holder.binding.dayOfBirth2TV.text = item.dateOfBirth
        holder.binding.gender2TV.text = item.gender
        holder.binding.house2TV.text = item.house


        if (item.isLiked) {
            holder.binding.likebTN.setImageResource(R.drawable.baseline_favorite_24)
        } else {
            holder.binding.likebTN.setImageResource(R.drawable.baseline_favorite_border_24)
        }

        holder.binding.likebTN.setOnClickListener {
            item.isLiked = !item.isLiked
            viewModel.updateLike(if (item.isLiked) 1 else 0, item.id)
        }


    }

    fun update(list: List<Character>) {
        dataset = list
        notifyDataSetChanged()
    }
}
