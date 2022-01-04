package com.remind.sampleapp.lorem_picsum.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.google.gson.Gson
import com.remind.sampleapp.lorem_picsum.api.response.ResImageInfo
import com.remind.sampleapp.lorem_picsum.api.service.LoremPicsumApiService
import com.remind.sampleapp.lorem_picsum.model.ImageInfo
import com.remind.sampleapp.lorem_picsum.repository.datasource.LoremPicsumDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LoremPicsumRepository(private val service: LoremPicsumApiService) {

    fun getImageInfo(imageId: String): Flow<ImageInfo> = flow {
        val response = service.imageInfo(imageId)
        if(response.isSuccessful) {
            kotlin.runCatching {
                Gson().fromJson(
                    response.body()?.string(),
                    ResImageInfo.Response::class.java)
            }.onSuccess {
                emit(it.mapper())
            }.onFailure {
                throw RuntimeException("invalid JSON File.")
            }
        } else {
            throw RuntimeException("response is Failed.")
        }
    }

    fun fetchImageList(): Flow<PagingData<ImageInfo>> {
        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = { LoremPicsumDataSource(service = service) }
        ).flow
    }

}