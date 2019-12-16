package com.quiz.client.model

import com.quiz.client.service.QuizApiService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
object AppModule {



        @Singleton
        @Provides
        fun provideRetrofitInstance(): Retrofit {
            return Retrofit.Builder().baseUrl("http://192.168.0.102:8082")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }



}