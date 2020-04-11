package bobby.irawan.simplenewsapp.presentation.view

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import bobby.irawan.simplenewsapp.R
import bobby.irawan.simplenewsapp.presentation.viewmodel.AboutMeViewModel

class AboutMeFragment : Fragment() {

    companion object {
        fun newInstance() = AboutMeFragment()
    }

    private lateinit var viewModel: AboutMeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.about_me_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AboutMeViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
