package com.remind.sampleapp.lorem_picsum.api.service

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface LoremPicsumApiService {

    @GET("/id/{imageId}/info")
    suspend fun imageInfo(@Path("imageId") imageId: String): Response<ResponseBody>
}