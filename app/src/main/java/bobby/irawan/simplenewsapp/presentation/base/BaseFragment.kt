package bobby.irawan.simplenewsapp.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getLayoutId(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        main(view, savedInstanceState)
    }

    abstract fun getLayoutId(): Int

    open fun main(view: View, savedInstanceState: Bundle?) {
        observeViewModelChanges()
    }

    abstract fun observeViewModelChanges()

}