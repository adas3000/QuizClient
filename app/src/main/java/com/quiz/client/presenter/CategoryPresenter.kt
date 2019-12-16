package com.quiz.client.presenter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.quiz.client.adapter.RecyclerViewAdapter
import com.quiz.client.service.QuizApiService
import com.quiz.client.view.ICategoryView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class CategoryPresenter : ICategoryPresenter {

    val iCategoryView:ICategoryView
    val retrofit:Retrofit
    val context:Context
    val rv:RecyclerView

    constructor(iCategoryView: ICategoryView,retrofit: Retrofit, context: Context,rv:RecyclerView) {
        this.iCategoryView = iCategoryView
        this.retrofit = retrofit
        this.context = context
        this.rv = rv
    }

    override fun onCategory(quizApiService: QuizApiService) {


        val call = quizApiService.listCategories()


        call.enqueue(object: Callback<List<String>> {
            override fun onFailure(call: Call<List<String>>, t: Throwable) {
                println("Failure")
            }

            override fun onResponse(call: Call<List<String>>, response: Response<List<String>>) {
                var adapter = RecyclerViewAdapter(response.body()!!.toList(),iCategoryView)
                rv.adapter = adapter
            }
        })


    }



}