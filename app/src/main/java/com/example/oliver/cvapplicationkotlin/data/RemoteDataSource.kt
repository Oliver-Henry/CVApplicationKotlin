package com.example.oliver.cvapplicationkotlin.data

import com.example.oliver.cvapplicationkotlin.utils.BASE_URL
import com.example.oliver.cvapplicationkotlin.data.network.WebService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RemoteDataSource : DataSource {

    private val webService: WebService by lazy {
        val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build()
        val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .build()
        retrofit.create(WebService::class.java)
    }

    override fun getListOfApplicants() = webService.getListOfApplicants()
}