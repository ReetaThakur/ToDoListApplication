package com.example.todolistapplication.ui

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.graphics.Color
import androidx.activity.viewModels

import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider

import com.example.todolistapplication.R
import com.example.todolistapplication.data.ToDoTable
import com.example.todolistapplication.databinding.ActivityAddTaskBinding

import com.example.todolistapplication.todoViewModel.TodoViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AddTaskActivity : AppCompatActivity() {
    private lateinit var activityAddTask :ActivityAddTaskBinding
    private lateinit var viewModel: TodoViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityAddTask=DataBindingUtil.setContentView(this,
            R.layout.activity_add_task)
        setStatusBar("Add Task", Color.LTGRAY)

        viewModel = ViewModelProvider(this)[TodoViewModel::class.java]


        activityAddTask.btnAddTask.setOnClickListener {
            insertTask()
            val intent= Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun insertTask(){
        var toDoTable=ToDoTable(activityAddTask.txtTask.text.toString(),
            activityAddTask.txtDescription.text.toString())
        CoroutineScope(Dispatchers.IO).launch {
            viewModel.insertTask(toDoTable)
        }

    }

    private fun setStatusBar(title:String, color: Int){
        supportActionBar?.title = title
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window: Window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = color
        }
    }
}