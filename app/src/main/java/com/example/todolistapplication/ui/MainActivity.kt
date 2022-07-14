package com.example.todolistapplication.ui

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolistapplication.R
import com.example.todolistapplication.data.ToDoTable
import com.example.todolistapplication.databinding.ActivityMainBinding
import com.example.todolistapplication.todoViewModel.TodoViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity(),ClickListner {
    private lateinit var mainBinding:ActivityMainBinding
    private lateinit var dialog: Dialog
    private lateinit var viewModel: TodoViewModel
    private var todoList =ArrayList<ToDoTable>()
    private lateinit var todoAdapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding=DataBindingUtil.setContentView(this,
            R.layout.activity_main)
        setStatusBar("To-Do-List", Color.LTGRAY)
        Dialog(this).also { dialog = it }

        viewModel = ViewModelProvider(this)[TodoViewModel::class.java]

        mainBinding.btnAdd.setOnClickListener {
            val intent=Intent(this,AddTaskActivity::class.java)
            startActivity(intent)
        }

        viewModel.getAllTask().observe(this) {
            todoList.clear()
            todoList.addAll(it)
            setRecyclerView()
            todoAdapter.notifyDataSetChanged()
        }


    }



    private fun setRecyclerView() {
        todoAdapter= TodoAdapter(todoList,this)
        var linearMLayoutManager=LinearLayoutManager(this)
        var divider= DividerItemDecoration(this,DividerItemDecoration.VERTICAL)
        mainBinding.recyclerView.apply {
            adapter=todoAdapter
            layoutManager=linearMLayoutManager
            addItemDecoration(divider)
        }
    }

    private fun setStatusBar(title:String, color:Int){
        supportActionBar?.title = title
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window: Window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = color
        }
    }

    override fun deleteTheTask(todo: ToDoTable) {
        CoroutineScope(Dispatchers.IO).launch {
            viewModel.deleteTask(todo)
        }

    }

    override fun editTheTask(todo: ToDoTable) {
      dialog.setContentView(R.layout.activity_add_task)
        dialog.show()
        var taskTitle =dialog.findViewById<TextView>(R.id.txtTask)
        var taskDescription=dialog.findViewById<TextView>(R.id.txtDescription)
        var editbutton=dialog.findViewById<Button>(R.id.btnAddTask)
        taskTitle.text=todo.title
        taskDescription.text=todo.description
        editbutton.text="Edit Task"

        editbutton.setOnClickListener {
            var title=taskTitle.text.toString()
            var description=taskDescription.text.toString()
            todo.title=title
            todo.description=description
            viewModel.updateTask(todo)
            dialog.dismiss()
        }
    }
}