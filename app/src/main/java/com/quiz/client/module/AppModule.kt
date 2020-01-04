package com.quiz.client.module

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
object AppModule {

    //.baseUrl("http://192.168.0.103:8082")
    //.baseUrl("http://10.0.2.2:8082")
        @Singleton
        @Provides
        fun provideRetrofitInstance(): Retrofit {
            val okHttpClient = OkHttpClient()
            return Retrofit.Builder().baseUrl("http://192.168.0.103:8082")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
        }



}