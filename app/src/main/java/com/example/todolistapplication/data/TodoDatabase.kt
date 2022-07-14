package com.example.todolistapplication.data

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteOpenHelper


@Database(entities = [ToDoTable::class], version = 1)
abstract class TodoDatabase:RoomDatabase() {

    abstract fun getDao():TodoDao

//    companion object{
//
//        private var INSTANCE:TodoDatabase? = null
//
//        fun getDatabase(context: Context) :TodoDatabase{
//            return if (INSTANCE==null){
//                var builder= Room.databaseBuilder(context.applicationContext,
//                    TodoDatabase::class.java,"todoDatabase")
//                builder.fallbackToDestructiveMigration()
//                INSTANCE=builder.build()
//                INSTANCE!!
//            }else
//                INSTANCE!!
//        }
//    }
}