package de.syntaxinstitut.android_ww_template

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBar
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import de.syntaxinstitut.android_ww_template.databinding.ActivityMainBinding
import de.syntaxinstitut.android_ww_template.ui.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment: NavHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController

        binding.bottomNavigationView.setupWithNavController(navController)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.baseline_arrow_back_24)
        //supportActionBar?.setBackgroundDrawable(ColorDrawable(getColor(R.color.black)))

        // Hier werden NavOptions festgelegt via Builder und die Enter/Exit Animation gesetted
        val options = NavOptions.Builder()
            .setLaunchSingleTop(true)
            .setEnterAnim(R.anim.slide_in_top)
            .setExitAnim(R.anim.slide_out_bottom)
            .setPopEnterAnim(R.anim.slide_in_top)
            .setPopExitAnim(R.anim.slide_out_bottom)
            .build()

        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.homeFragment -> {
                    //wichtig! .navigate verwenden um options zu benutzen
                    navController.navigate(R.id.homeFragment, null, options)

                }

                else -> {
                    navController.navigate(R.id.favoritFragment2, null, options)
                }
            }
            true
        }
        // observer auf unseren Boolean wert um das BottomNav ein- oder auszublenden
        viewModel.showBottomNav.observe(this) {
            if (it) {
                // setzt die visibility auf VISIBLE
                binding.bottomNavigationView.isVisible = true
                binding.bottomNavigationView.animate().apply {
                    //fade in mit duration von einer Sekunde
                    duration = 1000
                    alpha(1f)
                }
            } else {
                binding.bottomNavigationView.animate().apply {
                    //fade out mit duration von einer Sekunde
                    duration = 1000
                    alpha(0f)
                }.withEndAction{
                    // setzt dei visibility auf GONE
                    binding.bottomNavigationView.isVisible = false
                }
            }
        }

    }


    // Back Pfeil - Actionbar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                val navController = findNavController(R.id.fragmentContainerView)
                navController.popBackStack()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    // Actionbar - titel setzen
    fun Fragment.setActionBarTitle(title: String) {
        val activity = requireActivity()
        if (activity is AppCompatActivity) {
            val actionBar: ActionBar? = activity.supportActionBar
            actionBar?.title = title
        }
    }

}
