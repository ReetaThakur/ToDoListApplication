package com.example.todolistapplication.todoViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.todolistapplication.data.ToDoTable
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(private val repository: Repository)
    :ViewModel() {

    fun insertTask(todo: ToDoTable){
        CoroutineScope(Dispatchers.IO).launch {
            repository.insertTask(todo)
        }

    }

    fun updateTask(todo: ToDoTable){
        repository.updateTask(todo)
    }

    fun deleteTask(todo: ToDoTable){
        repository.deleteTask(todo)
    }

//    fun getAllTask(): Flowable<List<ToDoTable>> {
//        return repository.getAllTask()
//    }

    fun getAllTask():Flowable<List<ToDoTable>>{
        return repository.getAllTask().switchMap { data ->
            Flowable.just(data)
        }
    }


}