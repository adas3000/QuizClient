package com.quiz.client.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.quiz.client.R
import com.quiz.client.model.Choice

class RecyclerViewAnswerAdapter(val choiceList:List<Choice>):RecyclerView.Adapter<RecyclerViewAnswerAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.asnwer_layout,parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return choiceList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder?.choiceName.text=choiceList[position].value

        

    }

    class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val choiceName = view.findViewById<TextView>(R.id.textView_Answer)
    }



}