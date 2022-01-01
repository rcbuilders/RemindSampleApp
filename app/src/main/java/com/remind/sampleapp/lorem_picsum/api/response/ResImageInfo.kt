package com.remind.sampleapp.lorem_picsum.api.response

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
            return ImageInfo(
                id = id,
                author = author,
                image_url = download_url
            )
        }

    }
}