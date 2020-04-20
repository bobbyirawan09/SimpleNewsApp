package bobby.irawan.simplenewsapp.repository

import bobby.irawan.simplenewsapp.presentation.model.NewsModelView

interface NewsRepositoryContract {

    suspend fun getHeadLineNews(): NewsModelView?

}