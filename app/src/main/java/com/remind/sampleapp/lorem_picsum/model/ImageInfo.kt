package com.remind.sampleapp.lorem_picsum.model

data class ImageInfo (
    val id: String?,
    val author: String?,
    val image_url: String?,
    val thumbnail_url: String?,
    var isLike: Boolean? = false
)