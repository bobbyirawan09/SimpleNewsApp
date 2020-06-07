package bobby.irawan.simplenewsapp.presentation.presentation.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import bobby.irawan.simplenewsapp.R
import bobby.irawan.simplenewsapp.databinding.FragmentAccountBinding
import bobby.irawan.simplenewsapp.presentation.base.BaseFragment
import bobby.irawan.simplenewsapp.presentation.viewmodel.AccountViewModel
import bobby.irawan.simplenewsapp.utils.Constants.RC_SIGN_IN
import bobby.irawan.simplenewsapp.utils.Constants.SocialMedia.FACEBOOK
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.custom_toolbar.view.*
import org.koin.android.ext.android.get
import org.koin.androidx.viewmodel.ext.android.viewModel


class AccountFragment : BaseFragment<FragmentAccountBinding>() {

    companion object {
        fun newInstance() =
            AccountFragment()
    }

    private val viewModel: AccountViewModel by viewModel()
    private val gso: GoogleSignInOptions = get()
    private lateinit var googleSigninClient: GoogleSignInClient

    private val firebaseAuth = FirebaseAuth.getInstance()
    private val currentUser = firebaseAuth.currentUser
    private var callbackManager = CallbackManager.Factory.create()

    override fun main(view: View, savedInstanceState: Bundle?) {
        super.main(view, savedInstanceState)
        setFacebookCallBackManager()
        initClickListener()
    }

    override fun getLayoutId(): Int = R.layout.fragment_account

    override fun observeViewModelChanges() {
        viewModel.toolbarTitle.observe(this, Observer {
            setToolbarTitle(it)
        })
    }

    private fun setFacebookCallBackManager() {
        callbackManager = CallbackManager.Factory.create()
        LoginManager.getInstance().registerCallback(
            callbackManager,
            object : FacebookCallback<LoginResult?> {
                override fun onSuccess(loginResult: LoginResult?) {
                    viewModel.onLoginWithSocialMedia(
                        loginResult?.accessToken?.token, FACEBOOK
                    )
                }

                override fun onCancel() {}
                override fun onError(exception: FacebookException) {}
            }
        )
    }

    private fun initClickListener() {
        val layoutSocialMedia = binding.layoutLogin.layoutSocialMedia
        layoutSocialMedia.constraintLayoutGoogle.setOnClickListener {
            googleSigninClient = GoogleSignIn.getClient(requireContext(), gso)
            val signInIntent = googleSigninClient.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)
        }
        layoutSocialMedia.constraintLayoutFacebook.setOnClickListener {
            LoginManager.getInstance().logInWithReadPermissions(
                this,
                listOf("user_photos", "email", "public_profile")
            );
        }

        binding.layoutLogin.textViewToRegister.setOnClickListener {
            //Navigate to register page
        }
        binding.layoutLogin.buttonLogin.setOnClickListener {
            val email = binding.layoutLogin.textInputEditTextEmail.text.toString()
            val password = binding.layoutLogin.textInputEditTextPassword.text.toString()
            viewModel.onLoginWithEmail(email, password)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager.onActivityResult(requestCode, resultCode, data);
        viewModel.onHandleActivityResult(requestCode, data)
    }

    private fun setToolbarTitle(title: String) {
        binding.toolbar.text_view_toolbar_title.text = title
    }

}
