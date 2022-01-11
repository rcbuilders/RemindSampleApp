package com.remind.sampleapp

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.GsonBuilder
import com.remind.sampleapp.lorem_picsum.api.service.LoremPicsumApiService
import com.remind.sampleapp.lorem_picsum.repository.LoremPicsumRepository
import com.remind.sampleapp.lorem_picsum.viewmodel.LoremPicsumViewModel
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiConstants {
    companion object {
        const val API_LOREM_PICSUM = "api.lorem_picsum"
    }
}

object AppModules {
    private val repositories = module {
        factory { LoremPicsumRepository(service = get(named(ApiConstants.API_LOREM_PICSUM))) }
    }
    private val viewModels = module {
        viewModel { LoremPicsumViewModel(repository = get()) }
    }
    private val etc = module {
        factory {
            GsonBuilder()
                .enableComplexMapKeySerialization()
                .setPrettyPrinting()
                .create()
        }
        single(named(ApiConstants.API_LOREM_PICSUM)) {
            Retrofit.Builder()
                .client(OkHttpClient.Builder().run {
                    addNetworkInterceptor(StethoInterceptor())
                    connectTimeout(10, TimeUnit.SECONDS)
                    readTimeout(10, TimeUnit.SECONDS)
                    writeTimeout(10, TimeUnit.SECONDS)
                    build()
                })
                .baseUrl("https://picsum.photos")
                .addConverterFactory(GsonConverterFactory.create(get()))
                .build()
                .create(LoremPicsumApiService::class.java)
        }
    }

    val modules = listOf(etc, repositories, viewModels)
}