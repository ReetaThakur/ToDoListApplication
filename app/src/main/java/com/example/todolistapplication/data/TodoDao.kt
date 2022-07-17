package com.example.todolistapplication.data

import androidx.lifecycle.LiveData
import androidx.room.*
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observer


@Dao
interface TodoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTask(todo:ToDoTable)

    @Delete
    fun deleteTask(todo:ToDoTable)

    @Update
    fun updateTask(todo: ToDoTable)

    @Query("select * from todoListTable")
    fun getAllTask():Flowable<List<ToDoTable>>

    @Query("delete from todoListTable")
    fun deleteAll()
}