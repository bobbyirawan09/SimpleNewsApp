package bobby.irawan.simplenewsapp.presentation.ui.newscategory

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import bobby.irawan.simplenewsapp.R
import bobby.irawan.simplenewsapp.databinding.FragmentNewsBinding
import bobby.irawan.simplenewsapp.presentation.base.BaseFragment
import bobby.irawan.simplenewsapp.presentation.viewmodel.NewsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewsCategoryFragment : BaseFragment<FragmentNewsBinding>() {

    companion object {
        fun newInstance() = NewsCategoryFragment()
    }

    val args: NewsCategoryFragmentArgs by navArgs()

    override fun main(view: View, savedInstanceState: Bundle?) {
        super.main(view, savedInstanceState)
        args.category
    }

    private val newsViewModel: NewsViewModel by viewModel()

    override fun getLayoutId(): Int = R.layout.fragment_news

    override fun observeViewModelChanges() {

    }

}
