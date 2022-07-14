package com.example.todolistapplication.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todolistapplication.R
import com.example.todolistapplication.data.ToDoTable

class TodoAdapter(private var todoList: ArrayList<ToDoTable>,var clickListner: ClickListner):
    RecyclerView.Adapter<TodoAdapter.TodoViewHolder>()
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
       val view=LayoutInflater.from(parent.context).inflate(R.layout.task_layout,parent,false)
        return TodoViewHolder(view,clickListner)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        var todo=todoList[position]
        holder.setData(todo )
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    class TodoViewHolder(itemView:View,var clickListner: ClickListner):RecyclerView.ViewHolder(itemView){
     var title:TextView=itemView.findViewById(R.id.tvTaskTitle)
        var description:TextView=itemView.findViewById(R.id.tvTaskDescription)
        var delete:ImageView=itemView.findViewById(R.id.imageDelete)
        var edit:ImageView=itemView.findViewById(R.id.imageEdit)

        fun setData(todo:ToDoTable){
            title.text=todo.title
            description.text=todo.description
            delete.setOnClickListener {
                clickListner.deleteTheTask(todo)
            }
            edit.setOnClickListener {
                clickListner.editTheTask(todo)
            }
        }
    }
}