package de.syntaxinstitut.android_ww_template.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import de.syntaxinstitut.android_ww_template.R
import de.syntaxinstitut.android_ww_template.adapter.HarryPotterAdapter
import de.syntaxinstitut.android_ww_template.adapter.SecondAdapter
import de.syntaxinstitut.android_ww_template.databinding.FragmentFavoritBinding

class FavoritFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels()

    private lateinit var binding: FragmentFavoritBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentFavoritBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setActionBarTitle("Favorites")

        var adapter = SecondAdapter(emptyList(), viewModel)
        binding.favoriteRV.adapter = adapter

        viewModel.getAllLiked().observe(viewLifecycleOwner) {
            adapter.update(it)
        }

        binding.favoriteRV.setHasFixedSize(true)

    }

    fun Fragment.setActionBarTitle(title: String) {
        val activity = requireActivity()
        if (activity is AppCompatActivity) {
            val actionBar: ActionBar? = activity.supportActionBar
            actionBar?.title = title
            actionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }


}