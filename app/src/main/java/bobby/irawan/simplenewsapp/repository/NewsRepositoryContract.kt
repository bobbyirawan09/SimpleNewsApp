package bobby.irawan.simplenewsapp.repository

import bobby.irawan.simplenewsapp.presentation.NewsModelView

interface NewsRepositoryContract {

    fun getHeadLineNews(): NewsModelView

}