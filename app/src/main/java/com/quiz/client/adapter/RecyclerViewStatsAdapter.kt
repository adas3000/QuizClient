package com.quiz.client.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.quiz.client.R
import com.quiz.client.model.Score

class RecyclerViewStatsAdapter(val scoreList:List<Score>):RecyclerView.Adapter<RecyclerViewStatsAdapter.ViewHolder>() {


    override fun getItemCount(): Int {
        return scoreList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.stat_name.setText(scoreList[position].id)
        holder.stat_score.setText((scoreList[position].score).toString())

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.player_stats_layout,parent,false)
        return ViewHolder(v)
    }



    class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val stat_name = view.findViewById<TextView>(R.id.p_stats_player_name)
        val stat_score = view.findViewById<TextView>(R.id.p_stats_player_score)
    }

}