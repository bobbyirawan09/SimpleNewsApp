package bobby.irawan.simplenewsapp.di

import bobby.irawan.simplenewsapp.presentation.viewmodel.AboutMeViewModel
import bobby.irawan.simplenewsapp.presentation.viewmodel.NewsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        NewsViewModel(get())
    }

    viewModel {
        AboutMeViewModel(get())
    }

}