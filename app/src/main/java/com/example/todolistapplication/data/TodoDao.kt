package com.example.todolistapplication.data

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface TodoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTask(todo:ToDoTable)

    @Delete
    fun deleteTask(todo:ToDoTable)

    @Update
    fun updateTask(todo: ToDoTable)

    @Query("select * from todoListTable")
    fun getAllTask():LiveData<List<ToDoTable>>
}