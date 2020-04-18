package bobby.irawan.simplenewsapp.presentation.view

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import bobby.irawan.simplenewsapp.R
import bobby.irawan.simplenewsapp.databinding.FragmentAboutMeBinding
import bobby.irawan.simplenewsapp.presentation.base.BaseFragment
import bobby.irawan.simplenewsapp.presentation.viewmodel.AboutMeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class AboutMeFragment : BaseFragment<FragmentAboutMeBinding>() {

    companion object {
        fun newInstance() = AboutMeFragment()
    }

    val aboutMeviewModel: AboutMeViewModel by viewModel()

    override fun main(view: View, savedInstanceState: Bundle?) {
        super.main(view, savedInstanceState)
        initSocialMediaView()
    }

    private fun initSocialMediaView() {
        binding.includeSocialMediaEmail.imageViewSocialMedia.setImageResource(R.drawable.logo_gmail)
        binding.includeSocialMediaEmail.textViewSocialMedia.text =
            resources.getString(R.string.creator_email)
        binding.includeSocialMediaEmail.textViewSocialMedia.setTextColor(
            ContextCompat.getColor(
                context!!,
                R.color.black
            )
        )

        binding.includeSocialMediaGithub.imageViewSocialMedia.setImageResource(R.drawable.logo_github)
        binding.includeSocialMediaGithub.constraintLayoutParent.setBackgroundColor(
            ContextCompat.getColor(
                context!!,
                R.color.background_gray_dark
            )
        )
        binding.includeSocialMediaGithub.textViewSocialMedia.text =
            resources.getString(R.string.social_media_github)

        binding.includeSocialMediaLinkedin.imageViewSocialMedia.setImageResource(R.drawable.logo_linkedin)
        binding.includeSocialMediaLinkedin.constraintLayoutParent.setBackgroundColor(
            ContextCompat.getColor(
                context!!,
                R.color.background_linkedin_blue
            )
        )
        binding.includeSocialMediaLinkedin.textViewSocialMedia.text =
            resources.getString(R.string.social_media_linkedin)
    }

    override fun getLayoutId(): Int = R.layout.fragment_about_me

    override fun observeViewModelChanges() {
        //Do nothing here for now
    }


}
