package com.remind.sampleapp.lorem_picsum.api.response

abstract class BaseResponse<M> {
    abstract fun mapper(): M
}