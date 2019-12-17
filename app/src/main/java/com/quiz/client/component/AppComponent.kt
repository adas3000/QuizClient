package com.quiz.client.component

import com.quiz.client.module.AppModule
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun provideRetrofit():Retrofit

}