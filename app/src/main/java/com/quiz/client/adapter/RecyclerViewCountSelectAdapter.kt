package com.quiz.client.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.quiz.client.R
import java.util.*

class RecyclerViewCountSelectAdapter(val treeSet: TreeSet<Int>):RecyclerView.Adapter<RecyclerViewCountSelectAdapter.ViewHolder>() {


    override fun getItemCount(): Int {
        return treeSet.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.itemcount_layout,parent,false)
        return ViewHolder(v)
    }

    class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val value = view.findViewById<TextView>(R.id.textView_question_count_value)
    }


}