package bobby.irawan.simplenewsapp.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.plusAssign

/**
 * Created by bobbyirawan09 on 20/04/20.
 */
class CustomNavHostFragment: NavHostFragment() {
    override fun onCreateNavController(navController: NavController) {
        super.onCreateNavController(navController)
        navController.navigatorProvider += CustomNavigator(requireContext(), childFragmentManager, id)
    }
}