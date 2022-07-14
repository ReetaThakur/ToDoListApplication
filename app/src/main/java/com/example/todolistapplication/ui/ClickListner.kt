package com.example.todolistapplication.ui

import com.example.todolistapplication.data.ToDoTable

interface ClickListner {

    fun deleteTheTask(todo:ToDoTable)

    fun editTheTask(todo: ToDoTable)
}