package com.example.todolistapplication.di

import android.app.Application
import dagger.Provides
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext

@HiltAndroidApp
class TodoApp:Application() {

}