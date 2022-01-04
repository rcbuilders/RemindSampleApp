package com.remind.sampleapp.lorem_picsum.api.service

import com.remind.sampleapp.lorem_picsum.api.response.ResImageInfo
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface LoremPicsumApiService {

    @GET("/id/{imageId}/info")
    suspend fun imageInfo(@Path("imageId") imageId: String): Response<ResponseBody>

    @GET("/v2/list")
    suspend fun imageList(
        @Query("page") page: Int?,
        @Query("limit") limit: Int? = 20
    ): List<ResImageInfo.Response>
}