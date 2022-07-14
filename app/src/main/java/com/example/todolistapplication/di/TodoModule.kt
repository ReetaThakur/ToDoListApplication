package com.example.todolistapplication.di

import android.content.Context
import androidx.room.Room
import com.example.todolistapplication.data.TodoDao
import com.example.todolistapplication.data.TodoDatabase
import com.example.todolistapplication.todoViewModel.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TodoModule {

    @Singleton
    @Provides
    fun getDataBase(@ApplicationContext context: Context): TodoDatabase {
        val builder = Room.databaseBuilder(
            context,
            TodoDatabase::class.java, "todoDatabase"
        )
        builder.fallbackToDestructiveMigration()
        return builder.build()
    }

    @Singleton
    @Provides
    fun getDao(db: TodoDatabase): TodoDao {
        return db.getDao()
    }

}