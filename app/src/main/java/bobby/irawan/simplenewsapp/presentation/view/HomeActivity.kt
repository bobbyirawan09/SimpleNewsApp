package bobby.irawan.simplenewsapp.presentation.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import bobby.irawan.simplenewsapp.R
import bobby.irawan.simplenewsapp.databinding.ActivityMainBinding

class HomeActivity : AppCompatActivity() {

    private val binding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_main) as ActivityMainBinding
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setUpNavigation()
    }

    private fun setUpNavigation() {
        val navHostFragment: NavHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_home) as NavHostFragment
        binding.bottomNavigationHome.setupWithNavController(navHostFragment.navController)
    }
}
