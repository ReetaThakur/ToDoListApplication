package com.example.todolistapplication.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "todoListTable")
data class ToDoTable(@ColumnInfo(name = "title")var title:String,
@ColumnInfo(name = "description")var description:String) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")var id:Int?=null
}