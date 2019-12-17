package com.quiz.client.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.quiz.client.R
import com.quiz.client.model.Choice
import com.quiz.client.view.IChoiceView

class RecyclerViewAnswerAdapter(val choiceList:List<Choice>,val correctAnswer:String,val view:IChoiceView):RecyclerView.Adapter<RecyclerViewAnswerAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.asnwer_layout,parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return choiceList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val pos = (position+1).toString()+"."

        holder?.choiceName.text=pos+choiceList[position].value


        println("correct:"+correctAnswer)

        holder?.choiceName.setOnClickListener {
            var u_choice = holder?.choiceName.text.toString()
            u_choice = u_choice.substring(u_choice.indexOf(".")+1)

            println("user:"+u_choice)
            view.setNextQuestion(u_choice.equals(correctAnswer))
        }


    }

    class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val choiceName = view.findViewById<TextView>(R.id.textView_Answer)
    }



}