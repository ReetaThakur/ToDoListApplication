package com.example.todolistapplication.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.todolistapplication.todoViewModel.Repository
import com.example.todolistapplication.todoViewModel.TodoViewModel
import javax.inject.Inject


class TodoViewModelFactory @Inject constructor(private val repository: Repository):ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TodoViewModel(repository) as T
    }

}