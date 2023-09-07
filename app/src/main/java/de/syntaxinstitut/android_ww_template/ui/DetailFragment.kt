package de.syntaxinstitut.android_ww_template.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import coil.load
import de.syntaxinstitut.android_ww_template.R
import de.syntaxinstitut.android_ww_template.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels()

    private lateinit var binding: FragmentDetailBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        var id = requireArguments().getString("id")
        var item = viewModel.getById(id!!)

        viewModel.getById(id!!).observe(viewLifecycleOwner) {

            binding.imageView3.load(it.image)
            binding.birthday.text = "Day of Birth:  ${it.dateOfBirth}"
            binding.gender.text = "Gender:  ${it.gender}"
            binding.house.text = "House:  ${it.house}"
            binding.name.text = "Name:  ${it.name}"

            if (it.isLiked) {
                binding.imageButton2.setImageResource(R.drawable.baseline_favorite_24)
            } else {
                binding.imageButton2.setImageResource(R.drawable.baseline_favorite_border_24)
            }
        }

//        binding.imageButton2.setOnClickListener {
//            if (item == null){
//
//            }else {
//                item.value!!.isLiked = !item.value!!.isLiked
//                viewModel.updateLike(if (item.value!!.isLiked) 1 else 0,id)
//            }
//        }


    }

}

