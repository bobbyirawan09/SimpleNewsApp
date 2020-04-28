package bobby.irawan.simplenewsapp.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment


abstract class BaseFragment<dataBinding : ViewDataBinding> : Fragment() {

    protected lateinit var binding: dataBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity?)?.supportActionBar?.hide()
        main(view, savedInstanceState)
    }

    abstract fun getLayoutId(): Int

    open fun main(view: View, savedInstanceState: Bundle?) {
        observeViewModelChanges()
    }

    abstract fun observeViewModelChanges()

}