package com.quiz.client.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.quiz.client.R

class RecyclerViewHeaderAdapter(val list:List<Int>) : RecyclerView.Adapter<RecyclerViewHeaderAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val v: View = LayoutInflater.from(parent?.context).inflate(R.layout.topchar_layout,parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tv = view.findViewById<TextView>(R.id.textView_square)
    }

}