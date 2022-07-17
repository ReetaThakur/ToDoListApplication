package com.example.todolistapplication.todoViewModel

import androidx.lifecycle.LiveData
import com.example.todolistapplication.data.ToDoTable
import com.example.todolistapplication.data.TodoDao
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


class Repository @Inject constructor(private val dao: TodoDao) {

    fun insertTask(todo:ToDoTable){
        CoroutineScope(Dispatchers.IO).launch {
            dao.insertTask(todo)
        }

    }

    fun updateTask(todo: ToDoTable){
        CoroutineScope(Dispatchers.IO).launch {
            dao.updateTask(todo)
        }
    }

    fun deleteTask(todo:ToDoTable){
        dao.deleteTask(todo)
    }

    fun getAllTask():Flowable<List<ToDoTable>>{
        return dao.getAllTask()
    }
}