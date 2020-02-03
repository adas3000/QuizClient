package com.quiz.client.adapter


import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.quiz.client.R

class RecyclerViewHeaderAdapter(val count:Int,val topHeaderList:MutableList<Int> = mutableListOf())
    : RecyclerView.Adapter<RecyclerViewHeaderAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.topchar_layout,parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return count
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        if(position<topHeaderList.size) {
            if (topHeaderList[position] == 0)
                holder.tv.findViewById<TextView>(R.id.textView_square).setBackgroundColor(Color.parseColor("#E23636"))
            else if (topHeaderList[position] >= 1)
                holder.tv.findViewById<TextView>(R.id.textView_square).setBackgroundColor(Color.parseColor("#82DD55"))
        }

    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tv = view.findViewById<TextView>(R.id.textView_square)
    }

}