package com.remind.sampleapp.utils

import android.content.Intent

fun Intent.putLargeExtra(key: String, value: Any?) {
    value?.let {
        putExtra(key, ActivityHolder.putExtra(key, it))
    }
}

inline fun <reified T : Any> Intent.getLargeExtra(key: String): T? {
    return ActivityHolder.getExtra(key) as T?
}

object ActivityHolder {
    private val map: HashMap<String, Any> = hashMapOf()

    fun putExtra(key: String, data: Any): String {
        map[key] = data
        return key
    }

    fun getExtra(key: String): Any? {
        return map[key]
    }
}