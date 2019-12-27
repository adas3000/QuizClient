package com.quiz.client.presenter

import androidx.recyclerview.widget.RecyclerView
import com.quiz.client.adapter.RecyclerViewAdapter
import com.quiz.client.service.QuizApiService
import com.quiz.client.view.ICategoryView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryPresenter : ICategoryPresenter {

    val iCategoryView:ICategoryView
    val rv:RecyclerView

    constructor(iCategoryView: ICategoryView,rv:RecyclerView) {
        this.iCategoryView = iCategoryView
        this.rv = rv
    }

    override fun onCategory(quizApiService: QuizApiService) {


        val call = quizApiService.listCategories()


        call.enqueue(object: Callback<List<String>> {
            override fun onFailure(call: Call<List<String>>, t: Throwable) {
                println("Failure,msg:"+t.message)
            }

            override fun onResponse(call: Call<List<String>>, response: Response<List<String>>) {
                if(response.isSuccessful) println("response success")
                else println("response no success")
                var adapter = RecyclerViewAdapter(response.body()!!.toList(),iCategoryView)
                rv.adapter = adapter
            }
        })


    }



}