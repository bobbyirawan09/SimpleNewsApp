package bobby.irawan.simplenewsapp.presentation.news

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import bobby.irawan.simplenewsapp.R
import bobby.irawan.simplenewsapp.databinding.FragmentNewsDetailBinding
import bobby.irawan.simplenewsapp.presentation.base.BaseFragment
import bobby.irawan.simplenewsapp.presentation.viewmodel.NewsDetailViewModel

class NewsDetailFragment : BaseFragment<FragmentNewsDetailBinding>() {

    companion object {
        fun newInstance() =
            NewsDetailFragment()
    }

    private lateinit var viewModel: NewsDetailViewModel

    override fun main(view: View, savedInstanceState: Bundle?) {
        super.main(view, savedInstanceState)
    }

    override fun getLayoutId(): Int = R.layout.fragment_news_details

    override fun observeViewModelChanges() {
        TODO("Not yet implemented")
    }

}
