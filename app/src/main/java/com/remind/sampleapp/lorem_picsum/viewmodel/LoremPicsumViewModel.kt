package com.remind.sampleapp.lorem_picsum.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.remind.sampleapp.lorem_picsum.model.ImageInfo
import com.remind.sampleapp.lorem_picsum.repository.LoremPicsumRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class LoremPicsumViewModel(private val repository: LoremPicsumRepository): ViewModel() {

    private val _imageInfo: MutableLiveData<ImageInfo> = MutableLiveData()
    val imageInfo: LiveData<ImageInfo> get() = _imageInfo

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

}