package com.quiz.client.component

import com.quiz.client.model.AppModule
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun provideRetrofit():Retrofit

}