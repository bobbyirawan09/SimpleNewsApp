package bobby.irawan.simplenewsapp.presentation.view

import android.os.Bundle
import android.view.View
import bobby.irawan.simplenewsapp.R
import bobby.irawan.simplenewsapp.presentation.base.BaseFragment
import bobby.irawan.simplenewsapp.presentation.viewmodel.AboutMeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class AboutMeFragment : BaseFragment() {

    companion object {
        fun newInstance() = AboutMeFragment()
    }

    val aboutMeviewModel: AboutMeViewModel by viewModel()

    override fun main(view: View, savedInstanceState: Bundle?) {
        super.main(view, savedInstanceState)
    }

    override fun getLayoutId(): Int = R.layout.fragment_about_me

    override fun observeViewModelChanges() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}
