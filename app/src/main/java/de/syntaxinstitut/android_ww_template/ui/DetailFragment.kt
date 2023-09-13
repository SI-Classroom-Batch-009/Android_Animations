package de.syntaxinstitut.android_ww_template.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.BounceInterpolator
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
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

        setActionBarTitle("Character Detail")


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
        binding.imageView4.load(viewModel.characters.value?.get(1)?.image)

        binding.imageView3.setOnClickListener {
            // wir drehen beide imageViews um 90 Grad um die Y-Achse mit einer duration von 300 ms
            it.animate().apply {
                duration = 300
                rotationYBy(90f)
                binding.imageView4.animate().rotationYBy(90f)
            }.withEndAction {
                // macht die imageView3 durchsichtig
                it.alpha = 0f
                // setzt die visibility der imageView4 auf VISIBLE
                binding.imageView4.isVisible = true
                // wir drehen beide imageViews um 90 Grad um die Y-Achse mit einer duration von 300 ms
                binding.imageView4.animate().apply {
                    duration = 300
                    it.animate().rotationYBy(90f)
                    rotationXBy(90f)
                }
            }
        }
        binding.imageView4.setOnClickListener {
            // wir drehen beide imageViews um 90 Grad um die Y-Achse mit einer duration von 300 ms
            it.animate().apply {
                duration = 300
                rotationYBy(90f)
                binding.imageView3.animate().rotationYBy(90f)
            }.withEndAction {

                // hier wird die imageview4 auf visibility GONE gesetzt
                binding.imageView4.isVisible = false
                // hier wird die imageView3 wieder sichtbar gemacht
                binding.imageView3.alpha = 1f
                // wir drehen beide imageViews um 90 Grad um die Y-Achse mit einer duration von 300 ms
                binding.imageView3.animate().apply {
                    duration = 300
                    it.animate().rotationYBy(90f)
                    rotationYBy(90f)
                }
            }
        }
        binding.imageButton2.setOnClickListener {
            viewModel.switchVisibleBottomNav()
        }
        binding.name.setOnClickListener {
            binding.progressBar.incrementProgressBy(10)
            if (binding.progressBar.progress == 100) binding.progressBar.progress = 0
        }

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

