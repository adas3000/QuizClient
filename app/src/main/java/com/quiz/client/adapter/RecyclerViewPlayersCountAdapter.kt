package com.quiz.client.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.quiz.client.R
import com.quiz.client.presenter.IPlayersCountPresenter
import java.util.*

class RecyclerViewPlayersCountAdapter(val treeSet:TreeSet<Int>,val iPlayersCountPresenter: IPlayersCountPresenter)
    :RecyclerView.Adapter<RecyclerViewPlayersCountAdapter.ViewHolder>() {


    override fun getItemCount(): Int {
        return treeSet.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.value.setText(treeSet.elementAt(position).toString())

        holder.value.setOnClickListener {
            iPlayersCountPresenter.onCountSelect(treeSet.elementAt(position))
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.itemcount_layout,parent,false))
    }


    class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val value = view.findViewById<TextView>(R.id.textView_question_count_value)
    }


}