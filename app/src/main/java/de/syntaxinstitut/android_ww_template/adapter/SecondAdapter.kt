package de.syntaxinstitut.android_ww_template.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AccelerateInterpolator
import android.view.animation.AnticipateInterpolator
import android.view.animation.BounceInterpolator
import android.view.animation.CycleInterpolator
import android.view.animation.DecelerateInterpolator
import android.view.animation.LinearInterpolator
import android.view.animation.OvershootInterpolator
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

    inner class ItemViewHolder(val binding: ListItemFavoritBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding =
            ListItemFavoritBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        var item = dataset[position]

        holder.binding.potterIV.load(item.image)
        with(holder) {
            binding.name2TV.setText(R.string.Name)
            binding.houseTV.setText(R.string.House)
            binding.naame.text = item.name
            binding.houuse.text = item.house
        }


        if (item.isLiked) {
            holder.binding.likebTN.setImageResource(R.drawable.baseline_favorite_24)
        } else {
            holder.binding.likebTN.setImageResource(R.drawable.baseline_favorite_border_24)
        }
        var click = 0
        // Hier probieren wir die verschiedenen Interpolator aus und sorgen durch click dafür, dass sie auch wieder zurück gehen
        // würde ähnlich bei einem selbstanimierten switchbutton funktionieren
        holder.binding.likebTN.setOnClickListener {
            if (click % 2 == 0)
                when (position) {
                    0 -> it.animate()
                        .apply { translationXBy(-150f); interpolator = LinearInterpolator() }

                    1 -> it.animate()
                        .apply { translationXBy(-150f); interpolator = AccelerateInterpolator() }

                    2 -> it.animate()
                        .apply { translationXBy(-150f); interpolator = DecelerateInterpolator() }

                    3 -> it.animate()
                        .apply { translationXBy(-150f); interpolator = BounceInterpolator() }

                    4 -> it.animate()
                        .apply { translationXBy(-150f); interpolator = OvershootInterpolator() }

                    5 -> it.animate()
                        .apply { translationXBy(-150f); interpolator = AnticipateInterpolator() }

                    6 -> it.animate()
                        .apply { translationXBy(-150f); interpolator = CycleInterpolator(20f) }

                    7 -> it.animate().apply {
                        translationXBy(-150f); interpolator = AccelerateDecelerateInterpolator()
                    }

                } else
                when (position) {
                    0 -> it.animate()
                        .apply { translationXBy(150f); interpolator = LinearInterpolator() }

                    1 -> it.animate()
                        .apply { translationXBy(150f); interpolator = AccelerateInterpolator() }

                    2 -> it.animate()
                        .apply { translationXBy(150f); interpolator = DecelerateInterpolator() }

                    3 -> it.animate()
                        .apply { translationXBy(150f); interpolator = BounceInterpolator() }

                    4 -> it.animate()
                        .apply { translationXBy(150f); interpolator = OvershootInterpolator() }

                    5 -> it.animate()
                        .apply { translationXBy(150f); interpolator = AnticipateInterpolator() }

                    6 -> it.animate()
                        .apply { translationXBy(150f); interpolator = CycleInterpolator(20f) }

                    7 -> it.animate().apply {
                        translationXBy(150f); interpolator = AccelerateDecelerateInterpolator()
                    }
                }
            click++
        }



    }

    fun update(list: List<Character>) {
        dataset = list
        notifyDataSetChanged()
    }
}
