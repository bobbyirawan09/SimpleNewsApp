package bobby.irawan.simplenewsapp.repository

import androidx.lifecycle.LiveData
import bobby.irawan.simplenewsapp.presentation.NewsModelView

interface NewsRepositoryContract {

    suspend fun getHeadLineNews(): NewsModelView

}