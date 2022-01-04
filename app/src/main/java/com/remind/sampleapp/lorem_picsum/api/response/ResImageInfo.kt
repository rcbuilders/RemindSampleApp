package com.remind.sampleapp.lorem_picsum.api.response

import android.net.Uri
import com.remind.sampleapp.lorem_picsum.model.ImageInfo

class ResImageInfo {

    data class Response(
        val id: String?,
        val author: String?,
        val width: Int?,
        val height: Int?,
        val url: String?,
        val download_url: String?
    ): BaseResponse<ImageInfo> () {
        override fun mapper(): ImageInfo {

            val thumbnailUrl = download_url?.let {
                val uri = Uri.parse(it)
                "${uri.scheme}://${uri.host}/${uri.pathSegments[0]}/${uri.pathSegments[1]}/300/200"
            }
            return ImageInfo(
                id = id,
                author = author,
                image_url = download_url,
                thumbnail_url = thumbnailUrl
            )
        }

    }
}