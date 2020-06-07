package bobby.irawan.simplenewsapp.presentation.model

import android.net.Uri

/**
 * Created by bobbyirawan09 on 07/06/20.
 */
data class UserModelView(
    var uid: String? = "",
    var displayName: String? = "",
    var imageUrl: Uri? = null,
    var email: String? = ""
)