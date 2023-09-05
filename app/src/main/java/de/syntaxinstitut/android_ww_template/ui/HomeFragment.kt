package de.syntaxinstitut.android_ww_template.ui

import android.os.Binder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.SnapHelper
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

        binding = FragmentHomeBinding.inflate(inflater,container,false)
        viewModel.loadData()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.characters.observe(viewLifecycleOwner){
            binding.characterRV.adapter = HarryPotterAdapter(it,viewModel)
        }

        val helper: SnapHelper = PagerSnapHelper()
        helper.attachToRecyclerView(binding.characterRV)

        binding.characterRV.setHasFixedSize(true)

    }

}