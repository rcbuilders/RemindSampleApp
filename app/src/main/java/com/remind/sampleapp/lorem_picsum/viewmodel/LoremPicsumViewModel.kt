package com.remind.sampleapp.lorem_picsum.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.remind.sampleapp.lorem_picsum.api.response.ApiResult
import com.remind.sampleapp.lorem_picsum.model.ImageInfo
import com.remind.sampleapp.lorem_picsum.repository.LoremPicsumRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class LoremPicsumViewModel(private val repository: LoremPicsumRepository) : ViewModel() {

    private val _imageInfo: MutableLiveData<ImageInfo?> = MutableLiveData()
    val imageInfo: LiveData<ImageInfo?> get() = _imageInfo

    private val _errorMsg: MutableLiveData<String> = MutableLiveData()
    val errorMsg: LiveData<String> get() = _errorMsg

    fun getImageInfo(imageId: String) {
        viewModelScope.launch {
            repository.getImageInfo(imageId)
                .catch {
                    _errorMsg.postValue(it.message)
                }.collectLatest {
                    _imageInfo.postValue(it)
                }
        }
    }

    fun getImageInfoSafeFlow(imageId: String) {
        viewModelScope.launch {
            repository.getImageInfoSafeFlow(imageId).collectLatest { result ->
                when (result) {
                    is ApiResult.Success -> {
                        _imageInfo.postValue(result.value)
                    }
                    is ApiResult.Empty -> {
                        _errorMsg.postValue("data empty.")
                    }
                    is ApiResult.Error -> {
                        _errorMsg.postValue(result.exception?.message)
                    }
                }
            }
        }
    }

    fun fetchImageList(): Flow<PagingData<ImageInfo>> {
        return repository.fetchImageList()
    }

}