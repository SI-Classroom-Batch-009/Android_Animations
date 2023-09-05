package de.syntaxinstitut.android_ww_template.adapter

import android.app.LauncherActivity.ListItem
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import de.syntaxinstitut.android_ww_template.data.datamodels.Character
import de.syntaxinstitut.android_ww_template.databinding.ListItemBinding
import de.syntaxinstitut.android_ww_template.ui.MainViewModel

class HarryPotterAdapter(
    private var dataset: List<Character>,
    private var viewModel: MainViewModel,
) : RecyclerView.Adapter<HarryPotterAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        val item = dataset[position]

        holder.binding.imageView.load(item.image)
        holder.binding.nameTV.text = item.name

    }
}