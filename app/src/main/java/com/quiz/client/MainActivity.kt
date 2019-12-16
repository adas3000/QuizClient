package com.quiz.client

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.ButterKnife
import butterknife.OnClick
import com.quiz.client.adapter.RecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ButterKnife.bind(this)


        val rv = rv_Categories
        rv.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        val categories = ArrayList<String>()
        categories.add("Football")
        categories.add("Basketball")
        categories.add("Books")
        categories.add("Comics")
        categories.add("Vodka")
        categories.add("DeSth")


        var adapter = RecyclerViewAdapter(categories)

        rv.adapter = adapter


    }




}
