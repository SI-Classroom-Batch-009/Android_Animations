package de.syntaxinstitut.android_ww_template.ui

import android.os.Binder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.SnapHelper
import de.syntaxinstitut.android_ww_template.MainActivity
import de.syntaxinstitut.android_ww_template.R
import de.syntaxinstitut.android_ww_template.adapter.HarryPotterAdapter
import de.syntaxinstitut.android_ww_template.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels()

    private lateinit var binding: FragmentHomeBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(inflater, container, false)

        viewModel.loadData()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setActionBarTitle("Characters")


        var adapter = HarryPotterAdapter(emptyList(), viewModel)
        binding.characterRV.adapter = adapter

        viewModel.characters.observe(viewLifecycleOwner) {
            adapter.update(it)
        }

        binding.characterRV.setHasFixedSize(true)

    }

    fun Fragment.setActionBarTitle(title: String) {
        val activity = requireActivity()
        if (activity is AppCompatActivity) {
            val actionBar: ActionBar? = activity.supportActionBar
            actionBar?.title = title
            actionBar?.setDisplayHomeAsUpEnabled(false)
        }
    }

}