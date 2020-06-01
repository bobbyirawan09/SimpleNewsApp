package bobby.irawan.simplenewsapp.presentation.presentation.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import bobby.irawan.simplenewsapp.R
import bobby.irawan.simplenewsapp.databinding.FragmentAccountBinding
import bobby.irawan.simplenewsapp.presentation.base.BaseFragment
import bobby.irawan.simplenewsapp.presentation.viewmodel.AccountViewModel
import bobby.irawan.simplenewsapp.utils.Constants.RC_SIGN_IN
import bobby.irawan.simplenewsapp.utils.SharedPreferenceUtils
import bobby.irawan.simplenewsapp.utils.SharedPreferenceUtils.KEY_IS_USER_LOG_IN
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import kotlinx.android.synthetic.main.custom_toolbar.view.*
import org.koin.android.ext.android.get
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class AccountFragment : BaseFragment<FragmentAccountBinding>() {

    companion object {
        fun newInstance() =
            AccountFragment()
    }

    private val viewModel: AccountViewModel by viewModel()
    private val gso: GoogleSignInOptions by inject()
    private val isUserLogin =
        (SharedPreferenceUtils.sharedPref?.getBoolean(KEY_IS_USER_LOG_IN, false) ?: false)

    override fun getLayoutId(): Int = R.layout.fragment_account

    override fun main(view: View, savedInstanceState: Bundle?) {
        super.main(view, savedInstanceState)
        if (!isUserLogin) {
            binding.toolbar.text_view_toolbar_title.text = context?.getString(R.string.log_in_title)
        }

        val mGoogleSignInClient = GoogleSignIn.getClient(activity?.applicationContext!!, gso);
        var account = GoogleSignIn.getLastSignedInAccount(activity?.applicationContext!!)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            Toast.makeText(this.requireContext(), "LOL", Toast.LENGTH_SHORT).show()
        }
    }

    override fun observeViewModelChanges() {

    }

}
