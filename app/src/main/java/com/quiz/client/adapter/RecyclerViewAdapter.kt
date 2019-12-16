package com.quiz.client.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.quiz.client.R

class RecyclerViewAdapter(val categoryList:ArrayList<String>):RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder?.categoryName?.text = categoryList[position]
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.category_layout,parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    class ViewHolder(view:View) : RecyclerView.ViewHolder(view){
            val categoryName = view.findViewById<TextView>(R.id.textView_category)

    }



}